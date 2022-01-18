package com.example.shoppingmall.service;

import com.example.shoppingmall.domain.cart.Cart;
import com.example.shoppingmall.domain.cart.CartRepository;
import com.example.shoppingmall.domain.cart_item.Cart_item;
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


    // 유저 장바구니에 담긴 물품의 개수가 0개이고, 새로 추가할 때
    // 카트 하나를 생성함
    public void addItem(User user, Item item, int quantity) {
        // 만약 품절된 물건이라면?

        // 만약 같은 물건을 담는다면?


        if(cartRepository.findByUserId(user.getId()) == null) {
            // 유저ID에 대한 장바구니가 없을 때 == 즉 장바구니에 물건을 처음 담는 경우
            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
            // 여기까지 하면, cart(카트고유ID,유저) 가 담긴다.

            Cart_item cart_item = new Cart_item();
            cart_item.setCart(cart);
            cart_item.setItem(item);
            cart_item.setCount(quantity);
            cart_itemRepository.save(cart_item);
        } else {
            // 유저 ID에 대한 장바구니가 있을 때
            Cart userCart = cartRepository.findByUserId(user.getId());

            Cart_item cart_item = new Cart_item();
            cart_item.setCart(userCart);
            cart_item.setItem(item);
            cart_item.setCount(quantity);
            cart_itemRepository.save(cart_item);

        }

    }


}
