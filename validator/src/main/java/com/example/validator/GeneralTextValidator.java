package com.example.validator;

import com.example.validator.Interfaces.Validator;
import com.example.validator.Interfaces.ValidationRule;

import java.util.ArrayList;
import java.util.List;

public class GeneralTextValidator implements Validator {

    private final List<ValidationRule> validationRules;
    private String errorMessage;
//    private Boolean isRequired;

    public GeneralTextValidator() {
        this.validationRules = new ArrayList<>();
    }

    public void addValidationRule(ValidationRule rule) {
        validationRules.add(rule);
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

//    @Override
//    public void deliverContext(Context context) {
//        for (ValidationRule rule : validationRules) {
//            rule.deliverContext(context);
//        }
//    }

//    @Override
//    public void setRequired(Boolean isRequired) {
//        this.isRequired = isRequired;
//    }

    @Override
    public boolean isValid(String input) {
//        if(isRequired) {
        for (ValidationRule rule : validationRules) {
            if (!rule.isValid(input)) {
                setErrorMessage(rule.getErrorMessage());
                return false;
            }
        }
//        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
