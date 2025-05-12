package com.kyriewang.kkbbs.dto;

import com.kyriewang.kkbbs.entity.Question;

import java.util.List;

public class QuestionDto {
    private List<Question> questionDtos;
    private Integer currentPage;
    private Integer totalPages;
    // Getters and Setters
    public List<Question> getQuestionDtos() {
        return questionDtos;
    }

    public void setQuestionDtos(List<Question> questionDtos) {
        this.questionDtos = questionDtos;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
    