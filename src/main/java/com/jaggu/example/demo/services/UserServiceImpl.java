package com.jaggu.example.demo.services;

import com.jaggu.example.demo.DAO.UserDAO;
import com.jaggu.example.demo.POJO.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDAO usersDAO;

    @Override
    public void insertUser(Users usr) {
        usersDAO.insertUser(usr);
    }

    @Override
    public void insertUsers(List<Users> usr) {
        usersDAO.insertUsers(usr);
    }

    @Override
    public List<Users> getAllUsers() {
        List<Users> usr = usersDAO.getAllUsers();
        for(Users user : usr){
            System.out.println(user.toString());
        }
        return usr;
    }

    @Override
    public Users getUserById(int id) {
        return usersDAO.getUserById(id);
    }
}
