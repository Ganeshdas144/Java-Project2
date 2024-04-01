package com.org.project;

import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private List<String> options;
    private int correctOptionIndex;

    public MultipleChoiceQuestion(String questionText, List<String> options, int correctOptionIndex) {
        super(questionText, 10); 
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    @Override
    public List<String> getOptions() {
        return options;
    }

    @Override
    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

