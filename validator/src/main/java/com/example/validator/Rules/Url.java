//package com.example.validator.Rules;
//
//import android.webkit.URLUtil;
//
//public class Url implements ValidationRule {
//    @Override
//    public boolean isValid(String input) {
//        // Check if input is a valid URL using Android's URLUtil
//        return URLUtil.isValidUrl(input);
//    }
//
//    @Override
//    public String getErrorMessage() {
//        return "Invalid URL format";
//    }
//}

package com.example.validator.Rules;

import android.webkit.URLUtil;

import com.example.validator.Interfaces.ValidationRule;

import java.util.regex.Pattern;

public class Url implements ValidationRule {
    private String errorMessage;
    private final Pattern protocolPattern;
//    private Context context;

    public Url() {
        this("https?");
    }

    public Url(String allowedProtocols) {
        this.protocolPattern = Pattern.compile("^(" + allowedProtocols + ")://.*$");
    }

//    @Override
//    public void deliverContext(Context context) {
//        this.context = context;
//    }

    @Override
    public boolean isValid(String input) {
        if (!URLUtil.isValidUrl(input)) {
            this.errorMessage = /*context.getString(R.string.invalid_url_format)*/"Invalid URL format";
            return false;
        }

        if (!protocolPattern.matcher(input).matches()) {
            this.errorMessage = /*context.getString(R.string.url_must_use_an_allowed_protocol)*/"URL must use an allowed protocol";
            return false;
        }

        return true;
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