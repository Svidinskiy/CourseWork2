package com.example.QuestionGeneration;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);
    Question add(String question);
    Question remove(String question);
    Collection<Question> getAllQuestions();
    Question getRandomQuestion();
}
