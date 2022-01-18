package com.example.shoppingmall.service;

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
        itemRepository.deleteById(id);
    }

}
