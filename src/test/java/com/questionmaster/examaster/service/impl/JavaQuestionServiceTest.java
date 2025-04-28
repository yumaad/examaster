package com.questionmaster.examaster.service.impl;

import com.questionmaster.examaster.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private final JavaQuestionService service = new JavaQuestionService();

    private static Stream<Arguments> provideForAdd() {
        return Stream.of(
                Arguments.of("", ""),
                Arguments.of(" ", " "),
                Arguments.of("Q?", "A!")
        );
    }

    @Test
    void add_ShouldReturnAddedQuestion() {
        Question result = service.add("Q1", "A1");
        assertEquals(new Question("Q1", "A1"), result);
        assertTrue(service.getAll().contains(result));
    }

    @ParameterizedTest
    @MethodSource("provideForAdd")
    void add_ShouldHandleVariousCases(String question, String answer) {
        assertDoesNotThrow(() -> service.add(question, answer));
    }

    @Test
    void addDuplicate_ShouldReturnExistingQuestion() {
        Question q1 = service.add("Q1", "A1");
        Question q2 = service.add("Q1", "A1");
        assertEquals(q1, q2);
        assertEquals(1, service.getAll().size());
    }

    @ParameterizedTest
    @NullSource
    void add_ShouldThrowOnNull(Question question) {
        assertThrows(NullPointerException.class, () -> service.add(question));
    }

    @Test
    void remove_ShouldReturnRemovedQuestion() {
        Question q = service.add("Q2", "A2");
        Question removed = service.remove(q);
        assertEquals(q, removed);
        assertFalse(service.getAll().contains(q));
    }

    @Test
    void removeNonExisting_ShouldReturnNull() {
        assertNull(service.remove(new Question("Q3", "A3")));
    }

    @Test
    void getAll_ShouldReturnUnmodifiableCollection() {
        assertThrows(UnsupportedOperationException.class, () -> {
            Collection<Question> questions = service.getAll();
            questions.add(new Question("Q4", "A4"));
        });
    }

    @Test
    void getRandomQuestion_ShouldThrowWhenEmpty() {
        assertThrows(IllegalStateException.class, () -> service.getRandomQuestion());
    }

    @Test
    void getRandomQuestion_ShouldReturnQuestionFromCollection() {
        Question q = service.add("Q5", "A5");
        assertEquals(q, service.getRandomQuestion());
    }
}