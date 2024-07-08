package com.example.validator;

import androidx.annotation.ColorInt;

import java.util.ArrayList;
import java.util.List;

public class ValidationManager {

    private final List<CustomEditText> fields = new ArrayList<>();

    public void addField(CustomEditText field, Validator validator, @ColorInt int errorColor, @ColorInt int successColor) {
        field.setValidator(validator);
        field.setErrorColor(errorColor);
        field.setSuccessColor(successColor);
        fields.add(field);
    }

    public void addField(CustomEditText field, Validator validator, @ColorInt int color, String type) {
        if (type.contains(Constants.ERROR)) {
            addField(field, validator, color, Constants.DEFAULT_SUCCESS_COLOR);
        } else if (type.contains(Constants.SUCCESS)) {
            addField(field, validator, Constants.DEFAULT_ERROR_COLOR, color);
        }
    }

    public void addField(CustomEditText field, Validator validator) {
        addField(field, validator, Constants.DEFAULT_ERROR_COLOR, Constants.DEFAULT_SUCCESS_COLOR);
    }

    public boolean validate() {
        boolean allFieldsValid = true;
        for (CustomEditText field : fields) {
            if (!field.validate()) {
                allFieldsValid = false;
            }
        }
        return allFieldsValid;
    }
}
