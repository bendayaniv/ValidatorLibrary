package com.example.validator.Rules;

public class MustContainSpecialCharacterValidationRule implements ValidationRule {

    private final int minCount;

    public MustContainSpecialCharacterValidationRule(int minCount) {
        this.minCount = minCount;
    }

    @Override
    public boolean isValid(String input) {
        long specialCharCount = input.chars().filter(c -> !Character.isLetterOrDigit(c)).count();
        return specialCharCount >= minCount;
    }

    @Override
    public String getErrorMessage() {
        return "Input must contain at least " + minCount + " special character(s)";
    }
}
