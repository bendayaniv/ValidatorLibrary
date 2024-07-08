package com.example.validator;

import com.example.validator.Rules.ValidationRule;

import java.util.ArrayList;
import java.util.List;

public class GeneralTextValidator implements Validator {

    private final List<ValidationRule> validationRules;
    private String errorMessage;

    public GeneralTextValidator() {
        this.validationRules = new ArrayList<>();
    }

    public void addValidationRule(ValidationRule rule) {
        validationRules.add(rule);
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean isValid(String input) {
        for (ValidationRule rule : validationRules) {
            if (!rule.isValid(input)) {
                setErrorMessage(rule.getErrorMessage());
                return false;
            }
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
