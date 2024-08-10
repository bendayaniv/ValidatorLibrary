package com.example.validator.Rules;

import com.example.validator.Interfaces.ValidationRule;

public class NumericRange implements ValidationRule {
    private final Double min;
    private final Double max;
    private String errorMessage;
//    private Context context;

    public NumericRange(Double min, Double max) {
        this.min = min;
        this.max = max;
    }

    public static NumericRange min(double min) {
        return new NumericRange(min, null);
    }

    public static NumericRange max(double max) {
        return new NumericRange(null, max);
    }

    public static NumericRange between(double min, double max) {
        return new NumericRange(min, max);
    }

//    @Override
//    public void deliverContext(Context context) {
//        this.context = context;
//    }

    @Override
    public boolean isValid(String input) {
        try {
            double value = Double.parseDouble(input);
            if (min != null && value < min) {
                this.errorMessage = /*context.getString(R.string.value_must_be_at_least)*/"Value must be at least " + min;
                return false;
            }
            if (max != null && value > max) {
                this.errorMessage = /*context.getString(R.string.value_must_not_exceed)*/"Value must not exceed " + max;
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            this.errorMessage = /*context.getString(R.string.invalid_number_format)*/"Invalid number format";
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}