package com.takeHomeTest.checker.service.intrface;

import com.takeHomeTest.checker.dto.UserRequest;
import com.takeHomeTest.checker.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers (String name);
    User createUser (UserRequest request);
}
