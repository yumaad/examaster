package com.questionmaster.examaster.service;

import com.questionmaster.examaster.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}