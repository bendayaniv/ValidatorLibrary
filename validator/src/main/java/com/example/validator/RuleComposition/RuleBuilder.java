package com.example.validator.RuleComposition;

import com.example.validator.Interfaces.ValidationRule;
import com.example.validator.Rules.PriorityRule;

public class RuleBuilder {
    public static PriorityRule withPriority(ValidationRule rule, int priority) {
        return new PriorityRule(rule, priority);
    }

    public static AndRule and(ValidationRule... rules) {
        AndRule andRule = new AndRule();
        for (ValidationRule rule : rules) {
            andRule.addRule(rule);
        }
        return andRule;
    }

    public static OrRule or(ValidationRule... rules) {
        OrRule orRule = new OrRule();
        for (ValidationRule rule : rules) {
            orRule.addRule(rule);
        }
        return orRule;
    }

    public static NotRule not(ValidationRule rule) {
        return new NotRule(rule);
    }
}