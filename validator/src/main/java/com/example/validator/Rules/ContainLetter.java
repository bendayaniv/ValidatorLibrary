package com.example.validator.Rules;

public class ContainLetter implements ValidationRule {
    private final int minCount;

    public ContainLetter(int minCount) {
        this.minCount = minCount;
    }

    @Override
    public boolean isValid(String input) {
        long numberCount = input.chars().filter(Character::isLetter).count();
        return numberCount >= minCount;
    }

    @Override
    public String getErrorMessage() {
        return "Input must contain at least " + minCount + " letters(s)";
    }
}