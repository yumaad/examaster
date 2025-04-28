package com.questionmaster.examaster.service.impl;

import com.questionmaster.examaster.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExaminerServiceImplTest {
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        JavaQuestionService questionService = new JavaQuestionService();
        examinerService = new ExaminerServiceImpl(questionService);

        questionService.add("Q1", "A1");
        questionService.add("Q2", "A2");
        questionService.add("Q3", "A3");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void getQuestions_ShouldReturnRequestedAmount(int amount) {
        Collection<Question> questions = examinerService.getQuestions(amount);
        assertEquals(amount, questions.size());
    }

    @Test
    void getQuestions_ShouldReturnUniqueQuestions() {
        Collection<Question> questions = examinerService.getQuestions(2);
        assertEquals(2, new HashSet<>(questions).size());
    }

    @Test
    void getQuestions_ShouldThrowWhenNotEnoughQuestions() {
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> examinerService.getQuestions(5)
        );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Requested amount exceeds available questions count",
                exception.getReason());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void getQuestions_ShouldThrowForInvalidAmount(int amount) {
        assertThrows(ResponseStatusException.class,
                () -> examinerService.getQuestions(amount));
    }
}