package com.questionmaster.examaster.controller;

import com.questionmaster.examaster.model.Question;
import com.questionmaster.examaster.service.impl.JavaQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final JavaQuestionService service;

    public JavaQuestionController(JavaQuestionService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public Question addQuestion(
            @RequestParam String question,
            @RequestParam String answer) {
        return service.add(question, answer);
    }

    @GetMapping("/remove")
    public Question removeQuestion(
            @RequestParam String question,
            @RequestParam String answer) {
        return service.remove(new Question(question, answer));
    }

    @GetMapping
    public Collection<Question> getAllQuestions() {
        return service.getAll();
    }
}