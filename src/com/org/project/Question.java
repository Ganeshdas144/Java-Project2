package com.org.project;

import java.util.List;

public  abstract class Question {
    private String questionText;
    private int score;

    public Question(String questionText, int score) {
        this.questionText = questionText;
        this.score = score;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getScore() {
        return score;
    }

    public abstract List<String> getOptions();

    public abstract int getCorrectOptionIndex();
}
