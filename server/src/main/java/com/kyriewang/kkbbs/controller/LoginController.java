package com.kyriewang.kkbbs.controller;

import com.kyriewang.kkbbs.entity.User;
import com.kyriewang.kkbbs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private UserMapper userMapper;
//    测试
    @GetMapping("/cs")
    public List<User> cs(String username){
        List<User> list = userMapper.findByUsername2(username);
        System.out.println(list.get(0).getName());
        return list;
    }

    @PostMapping("/login")
    public List<User> login(@RequestBody User user) {
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        List<User> list = userMapper.findByUsername2(user.getName());
        System.out.println(list.get(0).getName());
        return list;
    }
}