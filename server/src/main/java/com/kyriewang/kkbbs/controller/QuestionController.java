package com.kyriewang.kkbbs.controller;

import com.kyriewang.kkbbs.dto.CommentDto;
import com.kyriewang.kkbbs.dto.queDto;
import com.kyriewang.kkbbs.entity.Comment;
import com.kyriewang.kkbbs.entity.Question;
import com.kyriewang.kkbbs.mapper.QuestionMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    
    @PostMapping("/comment")
    public ResponseEntity<String> addComment(@RequestBody CommentDto commentDto, @RequestHeader(value = "Authorization", required = false) String token) {
        try {
            // 将CommentDto转换为Comment实体
            Comment comment = new Comment();
            comment.setParent_id(commentDto.getParent_id());
            comment.setType(commentDto.getType());
            comment.setContent(commentDto.getContent());
            
            // 设置receiver_name，确保不为null
            String receiverName = commentDto.getReceiver_name();
            System.out.println("接收到的receiver_name: " + receiverName); // 调试日志
            if (receiverName == null || receiverName.isEmpty()) {
                // 如果是对问题的评论(type=1)，则获取问题创建者的用户名
                if (commentDto.getType() != null && commentDto.getType() == 1 && commentDto.getParent_id() != null) {
                    try {
                        // 获取问题ID对应的创建者ID
                        int creatorId = questionService.getcomuser(commentDto.getParent_id().intValue());
                        // 获取创建者用户名
                        receiverName = questionService.getcomuser2(creatorId);
                    } catch (Exception ex) {
                        // 如果获取失败，使用默认值
                        receiverName = "未知用户";
                    }
                } else {
                    // 默认接收者名称
                    receiverName = "未知用户";
                }
            }
            comment.setReceiver_name(receiverName);
            System.out.println("设置到Comment实体的receiver_name: " + comment.getReceiver_name()); // 调试日志
            
            // 处理receiver_id，确保不为null
            Long receiverId = commentDto.getReceiver_id();
            if (receiverId == null) {
                // 如果是对问题的评论(type=1)，则使用问题创建者ID作为接收者
                if (commentDto.getType() != null && commentDto.getType() == 1 && commentDto.getParent_id() != null) {
                    try {
                        // 获取问题ID对应的创建者ID
                        int creatorId = questionService.getcomuser(commentDto.getParent_id().intValue());
                        receiverId = Long.valueOf(creatorId);
                    } catch (Exception ex) {
                        // 如果获取失败，使用默认值
                        receiverId = 1L;
                    }
                } else {
                    // 默认接收者ID
                    receiverId = 1L;
                }
            }
            comment.setReceiver_id(receiverId);
            
            // 从token中获取用户ID，这里简化处理，实际应该从token解析用户信息
            Long userId = 1L; // 默认用户ID，实际应从token中获取
            if (token != null && !token.isEmpty()) {
                // 这里应该有token解析逻辑，获取真实用户ID
                // userId = tokenService.getUserIdFromToken(token);
            }
            
            comment.setComment_creator(userId);
            
            // 设置创建和修改时间
            Long currentTime = System.currentTimeMillis();
            comment.setGmt_create(currentTime);
            comment.setGmt_modified(currentTime);
            comment.setLike_count(0L);
            comment.setComment_count(0);
            
            // 保存评论
            questionService.insertComment(comment);
            
            return new ResponseEntity<>("评论成功", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("评论失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("commentId") Long commentId, @RequestHeader(value = "Authorization", required = false) String token) {
        try {
            // 从token中获取用户ID，这里简化处理，实际应该从token解析用户信息
            Long userId = 1L; // 默认用户ID，实际应从token中获取
            if (token != null && !token.isEmpty()) {
                // 这里应该有token解析逻辑，获取真实用户ID
                // userId = tokenService.getUserIdFromToken(token);
            }
            
            // 删除评论，只有评论创建者才能删除自己的评论
            int result = questionService.deleteComment(commentId, userId);
            
            if (result > 0) {
                return new ResponseEntity<>("删除评论成功", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("删除评论失败：您只能删除自己的评论", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("删除评论失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}