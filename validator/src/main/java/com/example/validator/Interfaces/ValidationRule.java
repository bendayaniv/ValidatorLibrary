package com.example.validator.Interfaces;

public interface ValidationRule {
//    void deliverContext(Context context);
    boolean isValid(String input);
    String getErrorMessage();
    int getPriority();
}
