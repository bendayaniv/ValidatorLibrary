package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

public class ContainNumber implements ValidationRule {
    private final int minCount;
//    private Context context;

    public ContainNumber(int minCount) {
        this.minCount = minCount;
    }

//    @Override
//    public void deliverContext(Context context) {
//        this.context = context;
//    }

    @Override
    public boolean isValid(String input) {
        long numberCount = input.chars().filter(Character::isDigit).count();
        return numberCount >= minCount;
    }

    @Override
    public String getErrorMessage() {
        return /*context.getString(R.string.input_must_contain_at_least)*/"Input must contain at least " + minCount + /*context.getString(R.string.number_s)*/" number(s)";
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
