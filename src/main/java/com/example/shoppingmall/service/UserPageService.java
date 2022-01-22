package com.example.shoppingmall.service;

import com.example.shoppingmall.domain.user.User;
import com.example.shoppingmall.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserPageService {

    private final UserRepository userRepository;

    public User findUser(Integer id) {
        return userRepository.findById(id).get();
    }

    public void userPageModify(User user) {
        User before = userRepository.findById(user.getId()); // 기존 유저
        before.setEmail(user.getEmail());
        before.setAddress(user.getAddress());
        before.setPhone(user.getPhone());

    }
}
