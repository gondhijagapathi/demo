package com.jaggu.example.demo.DAO;

import com.jaggu.example.demo.POJO.Users;

import java.util.List;

public interface UserDAO {
    void insertUser(Users usr);
    void insertUsers(List<Users> usr);
    List<Users> getAllUsers();
    Users getUserById(int id);
}
