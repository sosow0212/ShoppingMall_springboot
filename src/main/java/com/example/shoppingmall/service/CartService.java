package com.example.shoppingmall.service;

import com.example.shoppingmall.domain.cart.Cart;
import com.example.shoppingmall.domain.cart.CartRepository;
import com.example.shoppingmall.domain.cart_item.Cart_itemRepository;
import com.example.shoppingmall.domain.item.Item;
import com.example.shoppingmall.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartService {
    private final Cart_itemRepository cart_itemRepository;
    private final CartRepository cartRepository;

    public void aa(User user, Item item) {
        // 유저 장바구니에 담긴 물품의 개수가 0개이고, 새로 추가할 때
        Cart cart = new Cart();

        cart.setUser(user);
        cartRepository.save(cart);
        // 여기까지 하면, cart(카트고유ID,유저) 가 담긴다.
    }
}
