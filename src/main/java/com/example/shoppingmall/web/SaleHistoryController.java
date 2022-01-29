package com.example.shoppingmall.web;

import com.example.shoppingmall.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class SaleHistoryController {

    // 판매내역 페이지
    @GetMapping("/seller/{sellerId}/history/{userId}")
    public String salePageView(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable("sellerId}") Integer sellerId, @PathVariable("userId") Integer userId) {
        if(principalDetails.getUser().getId() != sellerId) {
            return "redirect:/main";
        }

        return "salePage";
    }
}
