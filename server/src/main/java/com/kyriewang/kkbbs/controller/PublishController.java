package com.kyriewang.kkbbs.controller;

import com.kyriewang.kkbbs.entity.Question;
import com.kyriewang.kkbbs.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private QuestionMapper questionService;

    @PostMapping("/add")
    public String addQuestion(@RequestBody Question question, HttpServletRequest request) {
        // 模拟获取用户 ID
        Long userId = 1L;
        question.setCreator(userId);
        
        // 设置创建时间和修改时间
        Date now = new Date();
        // 将Date转换为long类型的时间戳，以匹配数据库字段类型
        question.setGmtCreate(now.getTime());
        question.setGmtModified(now.getTime());
        
        questionService.insert(question);
        return "操作成功";
    }
    @GetMapping("/zj")
    public int insrtzj(String username){
        int userid = questionService.getuserid(username);
        int dec = questionService.getdescid();
        int result = questionService.result(userid,dec+ 1);
        return result;
    }
    @PostMapping("/{questionId}")
    public String updateQuestion(@PathVariable("questionId") Long questionId, @RequestBody Question question, HttpServletRequest request) {
        question.setId(questionId);
        
        // 设置修改时间
        Date now = new Date();
        // 将Date转换为long类型的时间戳，以匹配数据库字段类型
        question.setGmtModified(now.getTime());
        
        questionService.update(question);
        return "操作成功";
    }
}