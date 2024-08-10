package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

public class ContainSpecialCharacter implements ValidationRule {

    private final int minCount;
//    private Context context;

    public ContainSpecialCharacter(int minCount) {
        this.minCount = minCount;
    }

//    @Override
//    public void deliverContext(Context context) {
//        this.context = context;
//    }

    @Override
    public boolean isValid(String input) {
        long specialCharCount = input.chars().filter(c -> !Character.isLetterOrDigit(c)).count();
        return specialCharCount >= minCount;
    }

    @Override
    public String getErrorMessage() {
        return /*context.getString(R.string.input_must_contain_at_least)*/"Input must contain at least " + minCount + /*context.getString(R.string.special_character_s)*/" special character(s)";
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
