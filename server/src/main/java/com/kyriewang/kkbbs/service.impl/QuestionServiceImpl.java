//package com.kyriewang.kkbbs.service.impl;
//
//import com.kyriewang.kkbbs.dto.QuestionDto;
//import com.kyriewang.kkbbs.entity.Question;
//import com.kyriewang.kkbbs.mapper.QuestionMapper;
//import com.kyriewang.kkbbs.service.QuestionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class QuestionServiceImpl implements QuestionService {
//
//    @Autowired
//    private QuestionMapper questionMapper;
//
//    @Override
//    public QuestionDto findQuestionsByUserId(Long userId, Integer page, Integer size) {
//        Integer offset = (page - 1) * size;
//        List<Question> questionDtos = questionMapper.findQuestionsByUserId(userId, offset, size);
//        int totalCount = questionMapper.countQuestionsByUserId(userId);
//        int totalPages = (int) Math.ceil((double) totalCount / size);
//
//        QuestionDto dto = new QuestionDto();
//        dto.setQuestionDtos(questionDtos);
//        dto.setCurrentPage(page);
//        dto.setTotalPages(totalPages);
//        return dto;
//    }
//
//    @Override
//    public Question findById(Long id) {
//        return questionMapper.findById(id);
//    }
//
//    @Override
//    public void insert(Question question) {
//        questionMapper.insert(question);
//    }
//
//    @Override
//    public void update(Question question) {
//        questionMapper.update(question);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        questionMapper.deleteById(id);
//    }
//}
//