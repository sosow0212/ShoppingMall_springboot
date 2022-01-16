package com.example.shoppingmall.service;

import com.example.shoppingmall.domain.item.Item;
import com.example.shoppingmall.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    // 아이템 등록
    public void saveItem(Item item) {

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
    public void itemModify(Item item, Integer id) {
        Item before = itemRepository.findItemById(id);
        before.setName(item.getName());
        before.setText(item.getText());
        before.setPrice(item.getPrice());
        before.setStock(item.getStock());
        itemRepository.save(before);

    }


    // 아이템 삭제
    public void itemDelete(Integer id) {
        itemRepository.deleteById(id);
    }

}
