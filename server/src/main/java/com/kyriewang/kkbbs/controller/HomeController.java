package com.kyriewang.kkbbs.controller;


import com.kyriewang.kkbbs.entity.Question;
import com.kyriewang.kkbbs.mapper.QuestionMapper;
//import com.sun.glass.ui.Size;
//import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
public class HomeController {
@Autowired
private QuestionMapper  questionMapper;
    // 处理根路径 / 的 GET 请求
    @GetMapping("/")
    public List<Question> index(){
        List<Question> list = questionMapper.getq();
//        System.out.println(list.get(0).getTitle());
        return list;
    }
//    public ResultDto index(HttpServletRequest request,
//                           @RequestParam(name="page", defaultValue = "1") Integer page,
//                           @RequestParam(name="size", defaultValue = "5") Integer size,
//                           @RequestParam(name="search",required = false) String search,
//                           @RequestParam(name = "tag",required = false) String tag,
//                           @RequestParam(name = "sort",required = false) String sort){
//        PageDto pageDto = questionService.GetQuestionList(search,tag,sort,page,size);
//        return ResultDto.succ("首页查询成功",pageDto);
//    }

    // 处理 /hottags 的 GET 请求
    @GetMapping("/hottags")
    public Map<String, String> getHotTags() {
        Map<String, String> response = new HashMap<>();
        System.out.println(response);
        response.put("message", "Here are the hot tags!");
        return response;
    }
}