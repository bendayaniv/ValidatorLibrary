package com.example.validator.Rules;

public class Letters implements ValidationRule {
    @Override
    public boolean isValid(String input) {
        return input.matches("[a-zA-Z ]+");
    }

    @Override
    public String getErrorMessage() {
        return "Can only contain letters";
    }
}
