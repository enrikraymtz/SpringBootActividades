package com.enrikraymtz.springboot.service;

import java.util.Optional;

import com.enrikraymtz.springboot.model.User;

public interface UserService {
    public Optional<User> getByUsername(String username);
    
}
