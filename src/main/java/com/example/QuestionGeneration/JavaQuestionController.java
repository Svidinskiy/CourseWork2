package com.example.QuestionGeneration;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final JavaQuestionService questionService;

    public JavaQuestionController(JavaQuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam("question") String question, @RequestParam(value = "answer", required = false) String answer) {
        if (answer != null) {
            return questionService.add(question, answer);
        } else {
            return questionService.add(question);
        }
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam("question") String question) {
        return questionService.remove(question);
    }

    @GetMapping
    public Collection<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }
}

