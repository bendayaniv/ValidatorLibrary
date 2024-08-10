package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

public class Email implements ValidationRule {
    private static final String EMAIL_PATTERN =
            "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
//    private Context context;

//    @Override
//    public void deliverContext(Context context) {
//        this.context = context;
//    }

    @Override
    public boolean isValid(String input) {
        // Check if input is a valid email address
        return input.matches(EMAIL_PATTERN);
    }

    @Override
    public String getErrorMessage() {
        return /*context.getString(R.string.invalid_email_format)*/"Invalid email format";
    }

    @Override
    public int getPriority() {
        return 0;
    }
}