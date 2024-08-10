package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

public class PriorityRule implements ValidationRule {
    private final ValidationRule rule;
    private final int priority;

    public PriorityRule(ValidationRule rule, int priority) {
        this.rule = rule;
        this.priority = priority;
    }

    @Override
    public boolean isValid(String input) {
        return rule.isValid(input);
    }

    @Override
    public String getErrorMessage() {
        return rule.getErrorMessage();
    }

    @Override
    public int getPriority() {
        return priority;
    }
}