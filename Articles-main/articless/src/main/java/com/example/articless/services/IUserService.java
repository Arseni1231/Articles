package com.example.articless.services;

import com.example.articless.models.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();

    User getUserById(Long id);
}
