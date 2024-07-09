package com.example.validator.Rules;

public class Name implements ValidationRule {
    private String errorMessage;
    @Override
    public boolean isValid(String input) {
        // Check if the input is empty
        if (input.isEmpty()) {
            setErrorMessage("Name cannot be empty.");
            return false;
        }

        // Check if there is space in the first 3 characters
        if (input.length() > 3 && input.substring(0, 3).contains(" ")) {
            setErrorMessage("First 3 characters cannot include space.");
            return false;
        }

        // Check for consecutive spaces and apostrophes
        for (int i = 0; i < input.length() - 1; i++) {
            char current = input.charAt(i);
            char next = input.charAt(i + 1);
            if (current == ' ' && next == ' ') {
                setErrorMessage("Consecutive spaces are not allowed.");
                return false; // Consecutive spaces
            } else
            if (current == '\'' && next == ' ') {
                setErrorMessage("Apostrophe must be followed by a letter.");
                return false; // Apostrophe followed by space
            }
        }

        // Check if the whole input contains only letters, spaces, and apostrophes
        if (!input.matches("[a-zA-Z]+([' ][a-zA-Z]+)*")) {
            // Check if apostrophe ended the name
            if (input.endsWith("'")) {
                setErrorMessage("Apostrophe must be followed by a letter.");
                return false;
            }
            // Check if space ended the name
            if (input.endsWith(" ")) {
                setErrorMessage("Name cannot end with a space.");
                return false;
            }
            // Check for consecutive apostrophes
            if (input.contains("''")) {
                setErrorMessage("Consecutive apostrophes are not allowed.");
                return false;
            }
            setErrorMessage("Name must contain only letters, spaces, and apostrophes.");
            return false;
        }

        // Check for trailing space
        if (input.endsWith(" ")) {
            setErrorMessage("Name cannot end with a space.");
            return false;
        }

        // Check every word in the name if the word is a single character
        String[] words = input.split(" ");
        for (String word : words) {
            if (word.length() == 1) {
                setErrorMessage("Every word in the name must have more than one character.");
                return false;
            }
        }

        return true;
    }

    private void setErrorMessage(String message) {
        errorMessage = message;
    }

    @Override
    public String getErrorMessage() {
//        return "Can only contain letters, ' and spaces (spaces and ' cannot be consecutive and the first 3 character cannot include space)";
        return errorMessage;
    }
}
