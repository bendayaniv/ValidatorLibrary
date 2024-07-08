package com.example.validator.Rules;

public class MustContainNumberValidationRule implements ValidationRule {
    private final int minCount;

    public MustContainNumberValidationRule(int minCount) {
        this.minCount = minCount;
    }

    @Override
    public boolean isValid(String input) {
        long numberCount = input.chars().filter(Character::isDigit).count();
        return numberCount >= minCount;
    }

    @Override
    public String getErrorMessage() {
        return "Input must contain at least " + minCount + " number(s)";
    }
}
