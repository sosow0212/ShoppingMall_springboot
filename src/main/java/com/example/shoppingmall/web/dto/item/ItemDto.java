package com.example.shoppingmall.web.dto.item;

import com.example.shoppingmall.domain.item.Item;
import com.example.shoppingmall.domain.user.User;
import lombok.Data;

@Data
public class ItemDto {
    private String name;
    private String text;
    private int price;
    private int stock;

    public Item toEntity() {
        return Item.builder()
                .name(name)
                .text(text)
                .price(price)
                .stock(stock)
                .build();
    }
}

