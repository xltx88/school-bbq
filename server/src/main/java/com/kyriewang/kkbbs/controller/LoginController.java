package com.kyriewang.kkbbs.controller;

import com.kyriewang.kkbbs.entity.User;
import com.kyriewang.kkbbs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Object> login(@RequestBody User user) {
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        
        Map<String, Object> response = new HashMap<>();
        
        List<User> users = userMapper.findByUsername2(user.getName());
        
        // 检查用户是否存在
        if (users == null || users.isEmpty()) {
            response.put("code", 400);
            response.put("message", "用户不存在");
            return response;
        }
        
        User foundUser = users.get(0);
        
        // 验证密码是否匹配
        if (!foundUser.getPassword().equals(user.getPassword())) {
            response.put("code", 400);
            response.put("message", "密码错误");
            return response;
        }
        
        // 登录成功，返回用户信息
        response.put("code", 200);
        response.put("message", "登录成功");
        response.put("data", users);
        
        return response;
    }
    
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> registerInfo) {
        Map<String, Object> response = new HashMap<>();
        
        String username = registerInfo.get("username");
        String password = registerInfo.get("password");
        // String secondPassword = registerInfo.get("secondPassword");
        
        // // 验证两次密码是否一致
        // if (!password.equals(secondPassword)) {
        //     response.put("code", 400);
        //     response.put("message", "两次输入的密码不一致");
        //     return response;
        // }
        
        // 检查用户名是否已存在
        try {
            List<User> existingUsers = userMapper.findByUsername2(username);
            if (existingUsers != null && !existingUsers.isEmpty()) {
                response.put("code", 400);
                response.put("message", "用户名已存在");
                return response;
            }
        } catch (Exception e) {
            // 如果没有找到用户，可能会抛出异常，这是正常的
        }
        
        // 创建新用户
        User newUser = new User();
        newUser.setName(username);
        newUser.setPassword(password);
        
        try {
            // 假设UserMapper有一个save方法来保存用户
            userMapper.saveUser(newUser);
            response.put("code", 200);
            response.put("message", "注册成功");
        } catch (Exception e) {
            response.put("code", 500);
            response.put("message", "注册失败：" + e.getMessage());
        }
        
        return response;
    }
}