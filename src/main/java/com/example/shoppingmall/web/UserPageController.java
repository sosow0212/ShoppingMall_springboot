package com.example.shoppingmall.web;

import com.example.shoppingmall.config.auth.PrincipalDetails;
import com.example.shoppingmall.service.UserPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserPageController {
    private final UserPageService userPageService;

    // 유저 페이지
    @GetMapping("/user/{id}")
    public String userPage(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userPageService.findUser(id));
        return "/user/userPage";
    }
}
