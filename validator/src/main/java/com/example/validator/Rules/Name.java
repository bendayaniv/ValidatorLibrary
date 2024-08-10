package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

import java.util.regex.Pattern;

public class Name implements ValidationRule {
    private String errorMessage;
    private final int minLength;
    private final int maxLength;
    private final Pattern namePattern;
//    private Context context;

    public Name() {
        this(2, 50); // Default min and max lengths
    }

    public Name(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.namePattern = Pattern.compile("^[\\p{L}'][ \\p{L}'-]*[\\p{L}]$");
    }

//    @Override
//    public void deliverContext(Context context) {
//        this.context = context;
//    }

    @Override
    public boolean isValid(String input) {
        // Check if the input is empty
        if (input.isEmpty()) {
            setErrorMessage(/*context.getString(R.string.name_cannot_be_empty)*/"Name cannot be empty.");
            return false;
        }

        // Check if the name is too short or too long
        if (input.length() < minLength) {
            setErrorMessage(/*context.getString(R.string.name_must_be_at_least)*/"Name must be at least " + minLength + /*context.getString(R.string.characters_long)*/" characters long.");
            return false;
        }
        if (input.length() > maxLength) {
            setErrorMessage(/*context.getString(R.string.name_must_not_exceed)*/"Name must not exceed " + maxLength + /*context.getString(R.string.characters)*/" characters");
            return false;
        }

        // Check if there is space in the first 3 characters
        if (input.length() > 2 && input.substring(0, 2).contains(" ")) {
            setErrorMessage(/*context.getString(R.string.first_2_characters_cannot_include_space)*/"First 2 characters cannot include space.");
            return false;
        }

        // Check for consecutive spaces and apostrophes
        for (int i = 0; i < input.length() - 1; i++) {
            char current = input.charAt(i);
            char next = input.charAt(i + 1);
            if (current == ' ' && next == ' ') {
                setErrorMessage(/*context.getString(R.string.consecutive_spaces_are_not_allowed)*/"Consecutive spaces are not allowed.");
                return false; // Consecutive spaces
            } else if (current == '\'' && next == ' ') {
                setErrorMessage(/*context.getString(R.string.apostrophe_must_be_followed_by_a_letter)*/"Apostrophe must be followed by a letter.");
                return false; // Apostrophe followed by space
            }
        }

        // Check if the whole input contains only letters, spaces, and apostrophes
        if (!input.matches("[a-zA-Z]+([' ][a-zA-Z]+)*")) {
            // Check if apostrophe ended the name
            if (input.endsWith("'")) {
                setErrorMessage(/*context.getString(R.string.apostrophe_must_be_followed_by_a_letter)*/"Apostrophe must be followed by a letter.");
                return false;
            }
            // Check if space ended the name
            if (input.endsWith(" ")) {
                setErrorMessage(/*context.getString(R.string.name_cannot_end_with_a_space)*/"Name cannot end with a space.");
                return false;
            }
            // Check for consecutive apostrophes
            if (input.contains("''")) {
                setErrorMessage(/*context.getString(R.string.consecutive_apostrophes_are_not_allowed)*/"Consecutive apostrophes are not allowed.");
                return false;
            }
            setErrorMessage(/*context.getString(R.string.name_must_contain_only_letters_spaces_and_apostrophes)*/"Name must contain only letters, spaces, and apostrophes.");
            return false;
        }

        // Check for trailing space
        if (input.endsWith(" ")) {
            setErrorMessage(/*context.getString(R.string.name_cannot_end_with_a_space)*/"Name cannot end with a space.");
            return false;
        }

        // Check every word in the name if the word is a single character
        String[] words = input.split(" ");
        for (String word : words) {
            if (word.length() == 1) {
                setErrorMessage(/*context.getString(R.string.every_word_in_the_name_must_have_more_than_one_character)*/"Every word in the name must have more than one character.");
                return false;
            }
        }

        // New: Check if the name contains at least two words (first and last name)
        if (words.length < 2) {
            setErrorMessage(/*context.getString(R.string.please_provide_both_first_and_last_name)*/"Please provide both first and last name.");
            return false;
        }

        // New: Check if the name matches the extended pattern (allows for more international names)
        if (!namePattern.matcher(input).matches()) {
            setErrorMessage(/*context.getString(R.string.name_contains_invalid_characters_use_only_letters_spaces_hyphens_and_apostrophes)*/"Name contains invalid characters. Use only letters, spaces, hyphens, and apostrophes.");
            return false;
        }

        return true;
    }

    private void setErrorMessage(String message) {
        errorMessage = message;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}