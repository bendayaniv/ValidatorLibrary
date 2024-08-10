package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

public class MinLength implements ValidationRule {
    private final int minLength;
//    private Context context;

    public MinLength(int minLength) {
        this.minLength = minLength;
    }

//    @Override
//    public void deliverContext(Context context) {
//        this.context = context;
//    }

    @Override
    public boolean isValid(String input) {
        return input.length() >= minLength;
    }

    @Override
    public String getErrorMessage() {
        return /*context.getString(R.string.input_length_must_be_at_least)*/"Input length must be at least " + minLength + /*context.getString(R.string.characters)*/" characters";
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
