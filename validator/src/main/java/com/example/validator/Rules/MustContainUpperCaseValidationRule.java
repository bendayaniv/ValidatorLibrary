package com.example.validator.Rules;

public class MustContainUpperCaseValidationRule implements ValidationRule {
    private final int minCount;

    public MustContainUpperCaseValidationRule(int minCount) {
        this.minCount = minCount;
    }

    @Override
    public boolean isValid(String input) {
        long upperCaseCount = input.chars().filter(Character::isUpperCase).count();
        return upperCaseCount >= minCount;
    }

    @Override
    public String getErrorMessage() {
        return "Input must contain at least " + minCount + " uppercase letter(s)";
    }
}
