//package com.kyriewang.kkbbs.service.impl;
//
//import com.kyriewang.kkbbs.entity.User;
//import com.kyriewang.kkbbs.mapper.UserMapper;
//import com.kyriewang.kkbbs.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Override
//    public User findByUsername(String username) {
//        return userMapper.findByUsername(username);
//    }
//
//    @Override
//    public void updateUser(User user) {
//        userMapper.updateUser(user);
//    }
//
//    @Override
//    public List<User> getusr() {
//        return null;
//    }
//}
//