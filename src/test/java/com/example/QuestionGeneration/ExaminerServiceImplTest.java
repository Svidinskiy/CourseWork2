package com.example.QuestionGeneration;

import com.example.QuestionGeneration.exceptions.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;

    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        examinerService = new ExaminerServiceImpl(questionService);
    }

    @Test
    public void testGetQuestions() {
        List<Question> allQuestions = new ArrayList<>();
        allQuestions.add(new Question("Question 1", "Answer 1"));
        allQuestions.add(new Question("Question 2", "Answer 2"));
        allQuestions.add(new Question("Question 3", "Answer 3"));

        when(questionService.getAllQuestions()).thenReturn(allQuestions);

        int amount = 2;
        Collection<Question> result = examinerService.getQuestions(amount);

        assertNotNull(result);
        assertEquals(amount, result.size());
        assertTrue(allQuestions.containsAll(result));

        verify(questionService, times(1)).getAllQuestions();
    }

    @Test
    void testGetQuestionsWithInsufficientQuestions() {
        when(questionService.getAllQuestions())
                .thenReturn(Arrays.asList(
                        new Question("Question 1", "Answer 1"),
                        new Question("Question 2", "Answer 2")
                ));
        assertThrows(BadRequestException.class, () -> examinerService.getQuestions(3));
        verify(questionService, times(1)).getAllQuestions();
        verify(questionService, never()).getRandomQuestion();
    }
}
