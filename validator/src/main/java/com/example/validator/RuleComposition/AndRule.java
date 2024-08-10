package com.example.validator.RuleComposition;

import com.example.validator.Interfaces.CompositeValidationRule;
import com.example.validator.Interfaces.ValidationRule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AndRule implements CompositeValidationRule {
    private final List<ValidationRule> rules = new ArrayList<>();
    private String lastInput;

    @Override
    public void addRule(ValidationRule rule) {
        rules.add(rule);
        rules.sort(Comparator.comparingInt(ValidationRule::getPriority).reversed());
    }

    @Override
    public List<ValidationRule> getRules() {
        return new ArrayList<>(rules);
    }

    @Override
    public boolean isValid(String input) {
        this.lastInput = input;
        for (ValidationRule rule : rules) {
            if (!rule.isValid(input)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        for (ValidationRule rule : rules) {
            if (!rule.isValid(lastInput)) {
                return rule.getErrorMessage();
            }
        }
        return "";
    }

    @Override
    public int getPriority() {
//        return rules.isEmpty() ? 0 : rules.get(0).getPriority();
        return rules.stream()
                .mapToInt(ValidationRule::getPriority)
                .max()
                .orElse(0);
    }
}