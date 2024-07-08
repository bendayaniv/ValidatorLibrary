package com.example.validator.Rules;

public class MinLengthValidationRule implements ValidationRule {
    private final int minLength;

    public MinLengthValidationRule(int minLength) {
        this.minLength = minLength;
    }

    @Override
    public boolean isValid(String input) {
        return input.length() >= minLength;
    }

    @Override
    public String getErrorMessage() {
        return "Input length must be at least " + minLength + " characters";
    }
}
