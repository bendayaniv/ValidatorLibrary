package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

public class EndsWith implements ValidationRule {
    private final String suffix;

    public EndsWith(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public boolean isValid(String input) {
        return input.endsWith(suffix);
    }

    @Override
    public String getErrorMessage() {
        return "Input must end with '" + suffix + "'";
    }

    @Override
    public int getPriority() {
        return 0;
    }
}