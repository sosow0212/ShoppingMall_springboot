package com.example.shoppingmall.domain.cart;

import com.example.shoppingmall.domain.cart_item.Cart_item;
import com.example.shoppingmall.domain.item.Item;
import com.example.shoppingmall.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity // DB에 테이블 자동 생성
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    User user;

    @OneToMany(mappedBy = "cart")
    private List<Cart_item> cart_items = new ArrayList<>();
}