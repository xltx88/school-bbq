package com.kyriewang.kkbbs.service;

import com.kyriewang.kkbbs.dto.QuestionDto;
import com.kyriewang.kkbbs.entity.Comment;
import com.kyriewang.kkbbs.entity.Question;

public interface QuestionService {
    QuestionDto findQuestionsByUserId(Long userId, Integer page, Integer size);
    Question findById(Long id);
    void insert(Question question);
    void update(Question question);
    void deleteById(Long id);
    void insertComment(Comment comment);
}