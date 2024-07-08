package com.example.validator.Rules;

public class MaxLength implements ValidationRule {
    private final int maxLength;

    public MaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public boolean isValid(String input) {
        return input.length() <= maxLength;
    }

    @Override
    public String getErrorMessage() {
        return "Input length must not exceed " + maxLength + " characters";
    }
}
