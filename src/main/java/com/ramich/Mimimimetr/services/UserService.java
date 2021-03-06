package com.ramich.Mimimimetr.services;

import com.ramich.Mimimimetr.entities.User;

import java.util.List;

public interface UserService {
    boolean saveUser (User user);
    User findByUsername (String username);
    List<User> findAll();
    void updateUser(User user);
}
