package com.example.shoppingmall.web;

import com.example.shoppingmall.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class ItemController {

    private static ItemService itemService;

    // 메인 페이지
    @GetMapping({"/", "item"})
    public String mainPage(Model model) {
        model.addAttribute("items", itemService.allItemView());
        return "main";
    }

    // 아이템 상세 페이지
    @GetMapping("/item/{id}")
    public String itemView(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("item", itemService.itemView(id));
        return "item";
    }


}
