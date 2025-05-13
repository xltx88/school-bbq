package com.kyriewang.kkbbs.controller;

import com.kyriewang.kkbbs.dto.CommentDto;
import com.kyriewang.kkbbs.dto.queDto;
import com.kyriewang.kkbbs.entity.Question;
import com.kyriewang.kkbbs.mapper.QuestionMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
//@RequestMapping()
public class QuestionController {

    @Autowired
    private QuestionMapper questionService;

    @GetMapping("/question")
    public List<Question> findById(String id){
        List<Question> list = questionService.findById(id);

        return list;
    }
//    获取对应评论
    @GetMapping("/Comment")
    public List<CommentDto> getComment(String id){
        List<CommentDto> list = questionService.findPL(Integer.valueOf(id));
        return list;
    }
    @GetMapping("/allqu")
    public List<queDto> getallqu(String id){
        List<queDto> list = questionService.getallque(Integer.valueOf(id));
        return list;
    }
//    获取帖子作者
    @GetMapping("/getcomu")
    public String getcomu(String id){
        int userid = questionService.getcomuser(Integer.valueOf(id));
        String username = questionService.getcomuser2(userid);
        System.out.println(username);
        return username;
    }


    @DeleteMapping("/question/{questionId}")
    public String deleteQuestion(@PathVariable("questionId") Long questionId) {
        questionService.deleteById(questionId);
        return "删除成功";
    }
}