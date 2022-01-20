package com.example.shoppingmall.service;

import com.example.shoppingmall.domain.cart_item.Cart_item;
import com.example.shoppingmall.domain.item.Item;
import com.example.shoppingmall.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final CartService cartService;
    private final CartFinderService cartFinderService;

    // 아이템 등록
    public void saveItem(Item item, MultipartFile file) throws Exception {

        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files/";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        item.setFilename(fileName);
        item.setFilepath("/files/" + fileName);

        itemRepository.save(item);
    }


    // 아이템 리스트 불러오기
    public List<Item> allItemView() {
        return itemRepository.findAll();
    }

    // 아이템 개별로 불러오기
    public Item itemView(Integer id) {
        return itemRepository.findById(id).get();
    }


    // 아이템 수정
    public void itemModify(Item item, Integer id, MultipartFile file) throws Exception {
        // 아이템 수정할 때 장바구니에 담긴 아이템들 변동 사항에 맞춰 수정해야함
        // if) 아이템 물량을 0으로 맞춘다면?, 금액을 올려서 장바구니에 가격이 초과된다면?

        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files/";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);

        Item before = itemRepository.findItemById(id);
        before.setFilename(fileName);
        before.setFilepath("/files/" + fileName);
        before.setName(item.getName());
        before.setText(item.getText());
        before.setPrice(item.getPrice());
        before.setStock(item.getStock());
        itemRepository.save(before);

    }


    // 아이템 삭제
    public void itemDelete(Integer id) {
        // 유저의 장바구니에 물품이 담긴 경우 장바구니 삭제 후 물품 삭제

        List<Cart_item> items = cartFinderService.findCart_itemByItemId(id);
        // 아이템이 담긴 Cart_item 들

        for(Cart_item item : items) {
            cartService.deleteCart_item(item.getId());
        }

        itemRepository.deleteById(id);
    }

}
