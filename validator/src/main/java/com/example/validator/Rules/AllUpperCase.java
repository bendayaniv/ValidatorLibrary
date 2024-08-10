package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

public class AllUpperCase implements ValidationRule {
    @Override
    public boolean isValid(String input) {
        return input.equals(input.toUpperCase());
    }

    @Override
    public String getErrorMessage() {
        return "Input must be all uppercase";
    }

    @Override
    public int getPriority() {
        return 0;
    }
}