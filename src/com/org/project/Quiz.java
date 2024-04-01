package com.org.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private String title;
    private List<Question> questions;
    private Scanner scanner;

    public Quiz(String title, Scanner scanner) {
        this.title = title;
        this.questions = new ArrayList<>();
        this.scanner = scanner;
    }

    public String getTitle() {
        return title;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void startQuiz(User user) {
        int score = 0;
        long startTime = System.currentTimeMillis();

        System.out.println("\nQuiz started: " + title);
        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
            System.out.print("Enter your answer: ");
            int userAnswerIndex = scanner.nextInt() - 1;
            scanner.nextLine(); 

            if (userAnswerIndex >= 0 && userAnswerIndex < options.size()) {
                if (userAnswerIndex == question.getCorrectOptionIndex()) {
                    System.out.println("Your answer is correct.");
                    score += question.getScore();
                } else {
                    System.out.println("Incorrect answer.");
                }
            } else {
                System.out.println("Invalid answer index.");
            }
        }

        long endTime = System.currentTimeMillis();
        long durationSeconds = (endTime - startTime) / 1000;
        System.out.println("\nQuiz completed in " + durationSeconds + " seconds.");
        System.out.println("Total score: " + score);
    }
}
