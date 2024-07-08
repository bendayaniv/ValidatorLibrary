package com.example.validator.Rules;

public class MustContainLowerCaseValidationRule implements ValidationRule {
    private final int minCount;

    public MustContainLowerCaseValidationRule(int minCount) {
        this.minCount = minCount;
    }

    @Override
    public boolean isValid(String input) {
        long lowerCaseCount = input.chars().filter(Character::isLowerCase).count();
        return lowerCaseCount >= minCount;
    }

    @Override
    public String getErrorMessage() {
        return "Input must contain at least " + minCount + " lowercase letter(s)";
    }
}
