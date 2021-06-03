package com.jaggu.example.demo.controllers;

import com.jaggu.example.demo.POJO.Users;
import com.jaggu.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    UserService service;

    @GetMapping(value = "/users")
    List<Users> getAllUsers(){
        return service.getAllUsers();
    }

    @PostMapping(value = "/user")
    ResponseEntity postUsers(@RequestBody Users user){
        service.insertUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Single user inserted");
    }

    @PostMapping(value = "/users")
    ResponseEntity postListUsers(@RequestBody List<Users> users){
        service.insertUsers(users);
        return ResponseEntity.status(HttpStatus.CREATED).body("Multiple users inserted");
    }

    @GetMapping(value = "/user/{id}")
    ResponseEntity getUsersById(@PathVariable int id){
        try {
            Users user = service.getUserById(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body("{\"message\":\"User Not Found\"}");
        }
    }
}
