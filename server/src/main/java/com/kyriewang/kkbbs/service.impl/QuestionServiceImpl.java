// package com.kyriewang.kkbbs.service.impl;

// import com.kyriewang.kkbbs.dto.QuestionDto;
// import com.kyriewang.kkbbs.entity.Comment;
// import com.kyriewang.kkbbs.entity.Question;
// import com.kyriewang.kkbbs.mapper.QuestionMapper;
// import com.kyriewang.kkbbs.service.QuestionService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class QuestionServiceImpl implements QuestionService {

//     @Autowired
//     private QuestionMapper questionMapper;

//     @Override
//     public QuestionDto findQuestionsByUserId(Long userId, Integer page, Integer size) {
//         Integer offset = (page - 1) * size;
//         // 由于QuestionMapper.findQuestionsByUserId只接受一个参数，我们需要先获取所有问题，然后手动分页
//         List<Question> allQuestions = questionMapper.findQuestionsByUserId(userId);
//         // 手动实现分页逻辑
//         int startIndex = offset;
//         int endIndex = Math.min(startIndex + size, allQuestions.size());
//         List<Question> questionDtos = startIndex < endIndex ? 
//                                      allQuestions.subList(startIndex, endIndex) : 
//                                      java.util.Collections.emptyList();
//         int totalCount = questionMapper.countQuestionsByUserId(userId);
//         int totalPages = (int) Math.ceil((double) totalCount / size);

//         QuestionDto dto = new QuestionDto();
//         dto.setQuestionDtos(questionDtos);
//         dto.setCurrentPage(page);
//         dto.setTotalPages(totalPages);
//         return dto;
//     }

//     @Override
//     public Question findById(Long id) {
//         // 由于QuestionMapper.findById接受String参数并返回List<Question>，需要转换参数并处理返回值
//         List<Question> questions = questionMapper.findById(String.valueOf(id));
//         return questions != null && !questions.isEmpty() ? questions.get(0) : null;
//     }

//     @Override
//     public void insert(Question question) {
//         questionMapper.insert(question);
//     }

//     @Override
//     public void update(Question question) {
//         questionMapper.update(question);
//     }

//     @Override
//     public void deleteById(Long id) {
//         questionMapper.deleteById(id);
//     }
    
//     @Override
//     public void insertComment(Comment comment) {
//         questionMapper.insertComment(comment);
//     }
// }