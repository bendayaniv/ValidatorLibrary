package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Date implements ValidationRule {

    private final String dateFormat;
//    private String errorMessage;
//    private Context context;

    public Date(String dateFormat) {
        this.dateFormat = dateFormat;
    }

//    @Override
//    public void deliverContext(Context context) {
//        this.context = context;
//    }

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
        return /*context.getString(R.string.invalid_date_format_please_use)*/"Invalid date format. Please use " + this.dateFormat;
    }

    @Override
    public int getPriority() {
        return 0;
    }

//    public void setErrorMessage(String errorMessage) {
//        this.errorMessage = errorMessage;
//    }
}
