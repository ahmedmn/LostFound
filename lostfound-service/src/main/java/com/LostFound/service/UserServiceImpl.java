package com.LostFound.service;

import com.LostFound.entity.User;
import org.springframework.stereotype.Service;

/**
 * Only fake implementation of UserService because
 * project couldn't be compiled without no implementation of UserService
 */
@Service
public class UserServiceImpl implements UserService {
    public User findById(Long userId) {
        return null;
    }
}
