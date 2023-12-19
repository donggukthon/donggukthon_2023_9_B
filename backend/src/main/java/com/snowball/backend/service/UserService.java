package com.snowball.backend.service;

import com.snowball.backend.entity.User;
import com.snowball.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    // 유저 저장 또는 업데이트
    public void save(User user) {
        userRepository.save(user);
    }

    // 제공자 id로 유저를 찾아 반환
    public User getUserIdByProviderId(String providerId) {
        return userRepository.findUserByProviderId(providerId);
    }

    // 제공자 id로 유저를 찾아 반환
    public User getUserByUserId(Long UserId) {
        return userRepository.findUserByUserId(UserId);
    }
}