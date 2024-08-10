package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

public class GeneralNotContain implements ValidationRule {
    private final String substring;

    public GeneralNotContain(String substring) {
        this.substring = substring;
    }

    @Override
    public boolean isValid(String input) {
        return !input.contains(substring);
    }

    @Override
    public String getErrorMessage() {
        return "Input must not contain '" + substring + "'";
    }

    @Override
    public int getPriority() {
        return 0;
    }
}