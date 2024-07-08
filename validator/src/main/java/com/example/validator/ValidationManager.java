package com.example.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidationManager {

    private final List<CustomEditText> fields = new ArrayList<>();

    public void addField(CustomEditText field, Validator validator) {
        field.setValidator(validator);
        fields.add(field);
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
