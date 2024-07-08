package com.example.validator;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class CustomEditText extends TextInputEditText {

    private Validator validator;
    private int errorColor = 0xFFFF0000; // Default error color (Red)
    private int successColor = 0xFF00FF00; // Default success color (Green)

    public CustomEditText(Context context) {
        super(context);
        init(context, null);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText);

            String hint = a.getString(R.styleable.CustomEditText_android_hint);
            if (hint != null) {
                setHint(hint);
            }

            a.recycle();
        }
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public void setErrorColor(@ColorInt int errorColor) {
        this.errorColor = errorColor;
    }

    public void setSuccessColor(@ColorInt int successColor) {
        this.successColor = successColor;
    }

    public boolean validate() {
        if (validator == null) {
            throw new IllegalStateException("Validator not set");
        }

        String input = Objects.requireNonNull(getText()).toString();
        TextInputLayout parentLayout = (TextInputLayout) getParent().getParent();

        if (validator.isValid(input)) {
            setTextColor(successColor);
            setError(null);
            if (parentLayout != null) {
                parentLayout.setBoxStrokeColor(successColor);
                parentLayout.setHintTextColor(ColorStateList.valueOf(successColor));
            }
            return true;
        } else {
            setTextColor(errorColor);
            setError(validator.getErrorMessage());
            if (parentLayout != null) {
                parentLayout.setBoxStrokeColor(errorColor);
                parentLayout.setHintTextColor(ColorStateList.valueOf(errorColor));
            }
            return false;
        }
    }
}
