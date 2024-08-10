package com.example.validator.RuleComposition;

import com.example.validator.Interfaces.ValidationRule;

public class NotRule implements ValidationRule {
    private final ValidationRule rule;
    private String lastInput;

    public NotRule(ValidationRule rule) {
        this.rule = rule;
    }

    @Override
    public boolean isValid(String input) {
        this.lastInput = input;
        return !rule.isValid(input);
    }

    @Override
    public String getErrorMessage() {
        return "Not " + rule.getErrorMessage();
    }

    @Override
    public int getPriority() {
        return 0;
    }
}