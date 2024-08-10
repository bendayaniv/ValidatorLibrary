package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

public class ContainUpperCase implements ValidationRule {
    private final int minCount;
//    private Context context;

    public ContainUpperCase(int minCount) {
        this.minCount = minCount;
    }

//    @Override
//    public void deliverContext(Context context) {
//        this.context = context;
//    }

    @Override
    public boolean isValid(String input) {
        long upperCaseCount = input.chars().filter(Character::isUpperCase).count();
        return upperCaseCount >= minCount;
    }

    @Override
    public String getErrorMessage() {
        return /*context.getString(R.string.input_must_contain_at_least)*/"Input must contain at least " + minCount + /*context.getString(R.string.uppercase_letter_s)*/" uppercase letter(s)";
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
