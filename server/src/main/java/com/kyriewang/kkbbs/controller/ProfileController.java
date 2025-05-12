package com.kyriewang.kkbbs.controller;

import com.kyriewang.kkbbs.dto.NotificationDto;
import com.kyriewang.kkbbs.dto.QuestionDto;
import com.kyriewang.kkbbs.entity.Notification;
import com.kyriewang.kkbbs.entity.Question;
import com.kyriewang.kkbbs.entity.User;
import com.kyriewang.kkbbs.mapper.NotificationMapper;
import com.kyriewang.kkbbs.mapper.QuestionMapper;
import com.kyriewang.kkbbs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private QuestionMapper questionService;
    @Autowired
    private NotificationMapper notificationService;
    @Autowired
    private UserMapper userService;

    @GetMapping("/getusr")
    public List<User> getus() {
        return userService.getusr();
    }

    @GetMapping("/myquestion")
    public List<Question> getmyq(String username){
        int uid = questionService.getuserid(username);
        List<Question> list = questionService.getUserQue(uid);
        System.out.println(uid);
        return list;
    }

    @GetMapping("/message")
    public NotificationDto myMessages(@RequestParam("page") Integer page, HttpServletRequest request) {
        // 模拟获取用户 ID
        Long userId = 1L;
        return (NotificationDto) notificationService.findNotificationsByUserId(userId, page, 5);
    }

    @GetMapping("/unreadCount")
    public User unreadCount(HttpServletRequest request) {
        // 模拟获取用户 ID
        Long userId = 1L;
        return userService.findByUsername("test");
    }

    @PostMapping("/notification/{id}")
    public User readNotification(@PathVariable("id") Long id, HttpServletRequest request) {
        notificationService.updateStatus(id);
        return userService.findByUsername("test");
    }
}