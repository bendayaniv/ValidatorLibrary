package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

public class StartsWith implements ValidationRule {
    private final String prefix;

    public StartsWith(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public boolean isValid(String input) {
        return input.startsWith(prefix);
    }

    @Override
    public String getErrorMessage() {
        return "Input must start with '" + prefix + "'";
    }

    @Override
    public int getPriority() {
        return 0;
    }
}