package com.example.QuestionGeneration;

import com.example.QuestionGeneration.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        List<Question> allQuestions = new ArrayList<>(questionService.getAllQuestions());

        if (allQuestions.size() < amount) {
            throw new BadRequestException();
        }

        Set<Question> randomQuestions = new HashSet<>();
        Random random = new Random();

        while (randomQuestions.size() < amount) {
            int randomIndex = random.nextInt(allQuestions.size());
            randomQuestions.add(allQuestions.get(randomIndex));
        }

        return randomQuestions;
    }
}

