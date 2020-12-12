package com.example.service;

import com.example.bean.User;

import java.util.List;

public interface UserService {

    boolean save(User user);
    List<User> login(String username, String pwd);
    //List<User> login(Integer id,String username, String pwd);
    boolean delete(Integer id);
}
