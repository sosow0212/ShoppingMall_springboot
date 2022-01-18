package com.example.shoppingmall.web;

import com.example.shoppingmall.config.auth.PrincipalDetails;
import com.example.shoppingmall.domain.item.Item;
import com.example.shoppingmall.domain.user.User;
import com.example.shoppingmall.service.CartService;
import com.example.shoppingmall.service.ItemService;
import com.example.shoppingmall.service.UserPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserPageController {
    private final UserPageService userPageService;
    private final CartService cartService;
    private final ItemService itemService;


    // 유저 페이지
    @GetMapping("/user/{id}")
    public String userPage(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (principalDetails.getUser().getId() == id) {
            // 로그인 정보와 접속하는 유저 페이지의 id 값이 같으면 유저페이지 렌더링
            // 즉 본인은 본인 페이지만 볼 수 있음
            model.addAttribute("user", userPageService.findUser(id));
            return "/user/userPage";
        } else {
            return "redirect:/main";
        }

    }

    // 장바구니
    @GetMapping("/user/{id}/cart")
    public String userCart(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (principalDetails.getUser().getId() == id) {
            // 로그인 정보와 접속하는 유저 페이지의 id 값이 같으면 유저페이지 렌더링
            // 즉 본인은 본인 페이지만 볼 수 있음
            model.addAttribute("user", userPageService.findUser(id));
            return "/user/userCart";
        } else {
            return "redirect:/main";
        }
    }

    // 장바구니 추가
    @PostMapping("/user/{id}/cart/{itemId}")
    public String addCart(@PathVariable("id") Integer id, @PathVariable("itemId") Integer itemId, int quantity) {

        System.out.println("id == " + id + "  itemId == " + itemId + "  quantity == " + quantity);

        User loginUser = userPageService.findUser(id);
        Item item = itemService.itemView(itemId);

        cartService.addItem(loginUser, item, quantity);

        return "redirect:/item/{itemId}";
    }
}
