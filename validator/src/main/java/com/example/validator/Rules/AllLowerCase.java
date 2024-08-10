package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

public class AllLowerCase implements ValidationRule {
    @Override
    public boolean isValid(String input) {
        return input.equals(input.toLowerCase());
    }

    @Override
    public String getErrorMessage() {
        return "Input must be all lowercase";
    }

    @Override
    public int getPriority() {
        return 0;
    }
}