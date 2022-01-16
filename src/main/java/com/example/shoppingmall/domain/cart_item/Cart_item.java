package com.example.shoppingmall.domain.cart_item;

import com.example.shoppingmall.domain.cart.Cart;
import com.example.shoppingmall.domain.item.Item;
import com.example.shoppingmall.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity // DB에 테이블 자동 생성
public class Cart_item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="item_id")
    private Item item;

    private int count; // 카트에 담긴 상품 개수


}
