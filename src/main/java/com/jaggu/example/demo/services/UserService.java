package com.jaggu.example.demo.services;

import com.jaggu.example.demo.POJO.Users;

import java.util.List;

public interface UserService {
    void insertUser(Users usr);
    void insertUsers(List<Users> usr);
    List<Users> getAllUsers();
    Users getUserById(int id);
}
