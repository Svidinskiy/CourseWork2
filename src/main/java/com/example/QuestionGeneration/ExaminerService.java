package com.example.QuestionGeneration;

import java.util.Collection;
import java.util.List;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}

