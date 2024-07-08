package com.example.validator;

public interface Validator {
    boolean isValid(String input);
    String getErrorMessage();
}
