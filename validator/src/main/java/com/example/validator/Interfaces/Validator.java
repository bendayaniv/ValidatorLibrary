package com.example.validator.Interfaces;

public interface Validator {
//    void deliverContext(Context context);
//    void setRequired(Boolean isRequired);
    boolean isValid(String input);
    String getErrorMessage();
}
