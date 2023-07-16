package com.example.QuestionGeneration;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private Set<Question> questionSet;

    public JavaQuestionService() {
        questionSet = new HashSet<>();
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questionSet.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(String question) {
        return add(question, null);
    }

    @Override
    public Question remove(String question) {
        for (Iterator<Question> iterator = questionSet.iterator(); iterator.hasNext();) {
            Question q = iterator.next();
            if (q.getQuestion().equals(question)) {
                iterator.remove();
                return q;
            }
        }
        return null;
    }

    @Override
    public Collection<Question> getAllQuestions() {
        return questionSet;
    }

    @Override
    public Question getRandomQuestion() {
        if (questionSet.isEmpty()) {
            return null;
        }
        int size = questionSet.size();
        int randomIndex = new Random().nextInt(size);
        Iterator<Question> iterator = questionSet.iterator();
        for (int i = 0; i < randomIndex; i++) {
            iterator.next();
        }
        return iterator.next();
    }
}

