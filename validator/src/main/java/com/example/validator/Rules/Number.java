package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

public class Number implements ValidationRule {
//    private Context context;

//    @Override
//    public void deliverContext(Context context) {
//        this.context = context;
//    }

    @Override
    public boolean isValid(String input) {
        return input.matches("^\\d+$");
    }

    @Override
    public String getErrorMessage() {
        return /*context.getString(R.string.input_must_be_a_number)*/"Input must be a number";
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
