package com.example.validator.Rules;

import android.webkit.URLUtil;

public class Url implements ValidationRule {
    @Override
    public boolean isValid(String input) {
        // Check if input is a valid URL using Android's URLUtil
        return URLUtil.isValidUrl(input);
    }

    @Override
    public String getErrorMessage() {
        return "Invalid URL format";
    }
}