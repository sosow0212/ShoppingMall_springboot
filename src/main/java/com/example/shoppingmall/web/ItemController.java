package com.example.shoppingmall.web;

import com.example.shoppingmall.domain.item.Item;
import com.example.shoppingmall.service.ItemService;
import com.example.shoppingmall.web.dto.item.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ItemController {

    private final ItemService itemService;

    // 메인 페이지
    @GetMapping({"/", "/main"})
    public String mainPage(Model model) {
        List<Item> items = itemService.allItemView();
        model.addAttribute("items", items);
        return "main";
    }

    // 아이템 상세 페이지
    @GetMapping("/item/{id}")
    public String itemView(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("item", itemService.itemView(id));
        return "item";
    }

    // 아이템 업로드 페이지
    @GetMapping("/item/upload")
    public String itemUpload() {
        return "itemupload";
    }


    // 아이템 업로드 진행
    @PostMapping("/item/upload/process")
    public String itemUploadProcess(Item item) {
        itemService.saveItem(item);
        return "redirect:/main";
    }


    // 아이템 수정 페이지
    @GetMapping("/item/{id}/modify")
    public String itemModify(@PathVariable("id") Integer id) {
        return "itemmodify";
    }


    // 아이템 수정 처리
    @PostMapping("/item/{id}/modify/process")
    public String itemModifyProcess(Item item, @PathVariable("id") Integer id) {
        return "redirect:/main";
    }

    // 아이템 삭제
    @GetMapping("/item/{id}/delete")
    public String itemDelete(@PathVariable("id") Integer id) {
        itemService.itemDelete(id);
        return "redirect:/main";
    }


}
