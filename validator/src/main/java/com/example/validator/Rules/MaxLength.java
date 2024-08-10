package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

public class MaxLength implements ValidationRule {
    private final int maxLength;
//    private Context context;

    public MaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

//    @Override
//    public void deliverContext(Context context) {
//        this.context = context;
//    }

    @Override
    public boolean isValid(String input) {
        return input.length() <= maxLength;
    }

    @Override
    public String getErrorMessage() {
        return /*context.getString(R.string.input_length_must_not_exceed)*/"Input length must not exceed " + maxLength + /*context.getString(R.string.characters)*/" characters";
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
