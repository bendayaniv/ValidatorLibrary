package com.example.validator.Rules;

public class MaxLengthValidationRule implements ValidationRule {
    private final int maxLength;

    public MaxLengthValidationRule(int maxLength) {
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
