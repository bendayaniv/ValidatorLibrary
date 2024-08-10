package com.example.validator.Interfaces;

import java.util.List;

public interface CompositeValidationRule extends ValidationRule {
    void addRule(ValidationRule rule);
//    void removeRule(ValidationRule rule);
//    void clearRules();
//    boolean validate(String text);
    List<ValidationRule> getRules();
}
