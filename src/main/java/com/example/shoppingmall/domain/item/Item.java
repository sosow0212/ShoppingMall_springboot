package com.example.shoppingmall.domain.item;

import com.example.shoppingmall.domain.cart_item.Cart_item;
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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name; // 아이템 이름
    private String text; // 아이템 설명
    private int price; // 가격

    private String filename;
    private String filepath;

    // user_id // 판매자 아이디
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "item")
    private List<Cart_item> cart_items = new ArrayList<>();

    private boolean isSoldout; // ture = 매진
    private int stock; // 재고

    private int count; // 판매량

    //photo;
}
