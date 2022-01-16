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
}
