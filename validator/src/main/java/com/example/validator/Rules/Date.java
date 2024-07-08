package com.example.validator.Rules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Date implements ValidationRule {

    private final String dateFormat;
    private String errorMessage;

    public Date(String dateFormat) {
        this.dateFormat = dateFormat;
        this.errorMessage = "Invalid date format. Please use " + dateFormat;
    }

    @Override
    public boolean isValid(String input) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
        sdf.setLenient(false);
        try {
            sdf.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
