package com.questionmaster.examaster.service.impl;

import com.questionmaster.examaster.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExaminerServiceImplTest {
    private ExaminerServiceImpl examinerService;
    private JavaQuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
        examinerService = new ExaminerServiceImpl(questionService);
        questionService.add("Q1", "A1");
        questionService.add("Q2", "A2");
    }

    @Test
    void testGetQuestions() {
        Collection<Question> questions = examinerService.getQuestions(1);
        assertEquals(1, questions.size());
    }
}