package com.questionmaster.examaster.service.impl;

import com.questionmaster.examaster.model.Question;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private final JavaQuestionService service = new JavaQuestionService();

    @Test
    void testAddAndGetAll() {
        Question q = service.add("Q1", "A1");
        Collection<Question> all = service.getAll();
        assertEquals(1, all.size());
        assertTrue(all.contains(q));
    }

    @Test
    void testRemove() {
        Question q = service.add("Q2", "A2");
        assertNotNull(service.remove(q));
        assertEquals(0, service.getAll().size());
    }
}