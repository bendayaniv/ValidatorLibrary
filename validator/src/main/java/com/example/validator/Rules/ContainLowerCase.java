package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

public class ContainLowerCase implements ValidationRule {
    private final int minCount;
//    private Context context;

    public ContainLowerCase(int minCount) {
        this.minCount = minCount;
    }

//    @Override
//    public void deliverContext(Context context) {
//        this.context = context;
//    }

    @Override
    public boolean isValid(String input) {
        long lowerCaseCount = input.chars().filter(Character::isLowerCase).count();
        return lowerCaseCount >= minCount;
    }

    @Override
    public String getErrorMessage() {
        return /*context.getString(R.string.input_must_contain_at_least)*/"Input must contain at least " + minCount + /*context.getString(R.string.lowercase_letter_s)*/" lowercase letter(s)";
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
