package com.example.validator.RuleComposition;

import com.example.validator.Interfaces.CompositeValidationRule;
import com.example.validator.Interfaces.ValidationRule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrRule implements CompositeValidationRule {
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
            if (rule.isValid(input)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getErrorMessage() {
        ValidationRule highestPriorityFailedRule = null;
        StringBuilder errorMessage = new StringBuilder();
        for (ValidationRule rule : rules) {
            if (!rule.isValid(lastInput)) {
                if (highestPriorityFailedRule == null ||
                        rule.getPriority() > highestPriorityFailedRule.getPriority()) {
                    highestPriorityFailedRule = rule;
                    errorMessage.setLength(0);
                    errorMessage.append(rule.getErrorMessage());
                }
                else if (rule.getPriority() == highestPriorityFailedRule.getPriority()) {
                    errorMessage.append(" OR ");
                    errorMessage.append(rule.getErrorMessage());
                }
            }
        }
        return errorMessage.toString();
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