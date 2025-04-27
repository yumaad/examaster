package com.questionmaster.examaster.service.impl;

import com.questionmaster.examaster.model.Question;
import com.questionmaster.examaster.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        return questions.remove(question) ? question : null;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) throw new IllegalStateException("No questions available");
        int index = random.nextInt(questions.size());
        return questions.stream().skip(index).findFirst().orElseThrow();
    }
}