package com.takeHomeTest.checker.service;

import com.takeHomeTest.checker.dto.UserRequest;
import com.takeHomeTest.checker.entity.User;
import com.takeHomeTest.checker.repository.UserRepository;
import com.takeHomeTest.checker.service.intrface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers(String name) {
        return userRepository.findByNameIgnoreCase(name);
    }

    @Override
    public User createUser(UserRequest request) {
        User newUser = User.builder()
                .name(request.getName())
                .build();

        return userRepository.saveAndFlush(newUser);
    }
}
