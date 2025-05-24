package com.kyriewang.kkbbs.controller;


import com.kyriewang.kkbbs.entity.Question;
import com.kyriewang.kkbbs.entity.User;
import com.kyriewang.kkbbs.mapper.QuestionMapper;
//import com.sun.glass.ui.Size;
//import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.kyriewang.kkbbs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@CrossOrigin
@RestController
public class HomeController {
@Autowired
private QuestionMapper  questionMapper;
@Autowired
private UserMapper userMapper;
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

    @PostMapping("/userinformation")
    public void changeMessage(@RequestBody User user){
        // 设置创建时间和修改时间
        Date gmt_modified = new Date();
        // 将Date转换为long类型的时间戳，以匹配数据库字段类型
//        System.out.println(user.getName());
//        System.out.println(user.getAvatarUrl());
//        System.out.println(user.getId());
        userMapper.updUser(user.getName(),user.getAvatarUrl(),user.getId());


    }
//    修改密码
    @PostMapping("/changePass")
    public Map<String,String> changepassword(@RequestBody Map<String,String> map){
        Map<String,String> resp = new HashMap<>();
        System.out.println(map);
        int result = userMapper.upPaSS(map.get("newPassword"),Long.valueOf(map.get("id")));
        if(result > 0){
            resp.put("code","200");
        }else{
            resp.put("code","400");
        }
        return resp;

    }
//    获取图片
    @PostMapping("/getImg")
    public String getimg(@RequestBody @RequestParam("file") MultipartFile file){
        String filename = file.getOriginalFilename();
        String pathname =  UUID.randomUUID().toString()+filename ;
//        数据库内存储图片链接
        String databasepath = "http://localhost:8081/image/" + pathname;
        System.out.println(databasepath);
        Path filepath = Paths.get("C:\\Users\\xuefeng wang\\Desktop\\网页\\java期末大作业 社区讨论\\kkbbs\\kkbbs\\server\\target\\classes\\static");
        try {
            file.transferTo(filepath.resolve(pathname));
        } catch (IOException e) {
            System.out.println(e);
        }
        return databasepath;
    }
//    搜索
    @GetMapping("/search")
    public List<Question> search(String st){
        String s = "%" + st +  "%";
        List<Question> list = questionMapper.sousuo(s);
        return list;
    }


}