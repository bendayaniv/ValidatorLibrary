package com.example.validator.Rules;

public class Email implements ValidationRule {
    private static final String EMAIL_PATTERN =
            "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

    @Override
    public boolean isValid(String input) {
        // Check if input is a valid email address
        return input.matches(EMAIL_PATTERN);
    }

    @Override
    public String getErrorMessage() {
        return "Invalid email format";
    }
}