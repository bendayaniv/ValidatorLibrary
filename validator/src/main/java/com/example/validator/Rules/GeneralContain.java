package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

public class GeneralContain implements ValidationRule {
    private final String substring;

    public GeneralContain(String substring) {
        this.substring = substring;
    }

    @Override
    public boolean isValid(String input) {
        return input.contains(substring);
    }

    @Override
    public String getErrorMessage() {
        return "Input must contain '" + substring + "'";
    }

    @Override
    public int getPriority() {
        return 0;
    }
}