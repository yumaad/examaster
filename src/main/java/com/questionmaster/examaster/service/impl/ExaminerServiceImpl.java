package com.questionmaster.examaster.service.impl;

import com.questionmaster.examaster.model.Question;
import com.questionmaster.examaster.service.ExaminerService;
import com.questionmaster.examaster.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getAll().size()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Requested amount exceeds available questions count"
            );
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }
}