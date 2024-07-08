package com.example.validator.Rules;

public interface ValidationRule {
    boolean isValid(String input);
    String getErrorMessage();
}
