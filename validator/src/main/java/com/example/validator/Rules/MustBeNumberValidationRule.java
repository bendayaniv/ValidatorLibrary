package com.example.validator.Rules;

public class MustBeNumberValidationRule implements ValidationRule {
    @Override
    public boolean isValid(String input) {
        return input.matches("^\\d+$");
    }

    @Override
    public String getErrorMessage() {
        return "Input must be a number";
    }
}
