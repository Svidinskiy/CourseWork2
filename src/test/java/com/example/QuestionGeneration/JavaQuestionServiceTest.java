package com.example.QuestionGeneration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JavaQuestionServiceTest {
    private JavaQuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
    }

    @Test
    void testAddQuestionWithAnswer() {
        Question question = questionService.add("What is Java?", "Java is a programming language");
        assertEquals("What is Java?", question.getQuestion());
        assertEquals("Java is a programming language", question.getAnswer());
    }

    @Test
    void testAddQuestionWithoutAnswer() {
        Question question = questionService.add("What is Java?");
        assertEquals("What is Java?", question.getQuestion());
        assertEquals(null, question.getAnswer());
    }

    @Test
    void testRemoveQuestion() {
        Question question = questionService.add("What is Java?");
        assertEquals(question, questionService.remove("What is Java?"));
        assertEquals(0, questionService.getAllQuestions().size());
    }

    @Test
    void testGetAllQuestions() {
        questionService.add("Question 1");
        questionService.add("Question 2");
        questionService.add("Question 3");
        Collection<Question> questions = questionService.getAllQuestions();
        assertEquals(3, questions.size());
        Set<String> expectedQuestions = new HashSet<>(Arrays.asList("Question 1", "Question 2", "Question 3"));
        for (Question question : questions) {
            assertEquals(true, expectedQuestions.contains(question.getQuestion()));
        }
    }

    @Test
    void testGetRandomQuestionWithEmptyQuestionSet() {
        assertNull(questionService.getRandomQuestion());
    }

    @Test
    void testGetRandomQuestionWithNonEmptyQuestionSet() {
        Question question1 = questionService.add("Question 1", "Answer 1");
        Question question2 = questionService.add("Question 2", "Answer 2");
        Question question3 = questionService.add("Question 3", "Answer 3");

        Set<Question> randomQuestions = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            Question randomQuestion = questionService.getRandomQuestion();
            randomQuestions.add(randomQuestion);
        }

        assertEquals(3, randomQuestions.size());
        assertEquals(true, randomQuestions.contains(question1));
        assertEquals(true, randomQuestions.contains(question2));
        assertEquals(true, randomQuestions.contains(question3));
    }
}
