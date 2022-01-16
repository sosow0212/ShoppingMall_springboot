package com.example.shoppingmall.web;

import com.example.shoppingmall.config.auth.PrincipalDetails;
import com.example.shoppingmall.domain.item.Item;
import com.example.shoppingmall.service.ItemService;
import com.example.shoppingmall.service.UserPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class SellerPageController {
    private final UserPageService userPageService;
    private final ItemService itemService;

    @GetMapping("/seller/{id}")
    public String sellerPage(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails.getUser().getRole().equals("ROLE_USER")) {
            // 일반 유저가 판매관리 페이지로 올 경우 main으로 리턴
            return "redirect:/main";
        } else {
            // ROLE_ADMIN인 유저는 판매관리 렌더링 가능

            List<Item> allItem = itemService.allItemView();
            List<Item> userItem = new ArrayList<>();


            for(Item item : allItem ) {
                if(item.getUser().getId() == id) {
                    userItem.add(item);
                }
            }

            model.addAttribute("seller", userPageService.findUser(id));
            model.addAttribute("userItem", userItem);
            return "seller/sellerPage";
        }

    }
}
