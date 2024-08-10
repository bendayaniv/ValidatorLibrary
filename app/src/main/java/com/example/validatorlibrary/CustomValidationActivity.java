package com.example.validatorlibrary;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.validator.*;
import com.example.validator.Interfaces.ValidationRule;
import com.example.validator.RuleComposition.RuleBuilder;
import com.example.validator.Rules.*;
import com.example.validator.Rules.Number;

public class CustomValidationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_validation);

        CustomEditText creditCardEditText = findViewById(R.id.creditCardEditText);
        CustomEditText passwordStrengthEditText = findViewById(R.id.passwordStrengthEditText);
        CustomEditText palindromeEditText = findViewById(R.id.palindromeEditText);
        Button submitButton = findViewById(R.id.submitButton);
        Button backButton = findViewById(R.id.backButton);

        ValidationManager validationManager = new ValidationManager();

        // Custom Credit Card Validator with RuleBuilder
        ValidationRule luhnRule = new ValidationRule() {
            @Override
            public boolean isValid(String input) {
                // Simple Luhn algorithm check
                int sum = 0;
                boolean alternate = false;
                for (int i = input.length() - 1; i >= 0; i--) {
                    int n = Integer.parseInt(input.substring(i, i + 1));
                    if (alternate) {
                        n *= 2;
                        if (n > 9) {
                            n = (n % 10) + 1;
                        }
                    }
                    sum += n;
                    alternate = !alternate;
                }
                return (sum % 10 == 0);
            }

            @Override
            public String getErrorMessage() {
                return "Invalid credit card number";
            }

            @Override
            public int getPriority() {
                return 0;
            }
        };

        ValidationRule creditCardRule = RuleBuilder.and(
                new Number(),
                RuleBuilder.and(new MinLength(13), new MaxLength(19)),
                luhnRule
        );

        GeneralTextValidator creditCardValidator = new GeneralTextValidator();
        creditCardValidator.addValidationRule(creditCardRule);
        validationManager.addField(creditCardEditText, creditCardValidator, Color.parseColor("#FF7F50"), Color.parseColor("#32CD32"));

        // valid credit card - 3530111333300000
        // invalid credit card - 12345678901234567890 (too long)

        // Custom Password Strength Validator with RuleBuilder
        ValidationRule passwordStrengthRule = new ValidationRule() {
            @Override
            public boolean isValid(String input) {
                int strength = 0;
                if (input.length() >= 8) strength++;
                if (input.matches(".*\\d.*")) strength++;
                if (input.matches(".*[a-z].*")) strength++;
                if (input.matches(".*[A-Z].*")) strength++;
                if (input.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) strength++;
                return strength >= 4;
            }

            @Override
            public String getErrorMessage() {
                return "Password is not strong enough";
            }

            @Override
            public int getPriority() {
                return 0;
            }
        };

        ValidationRule compositePasswordRule = RuleBuilder.and(
                new MinLength(8),
                RuleBuilder.or(
                        passwordStrengthRule,
                        RuleBuilder.and(
                                new ContainLowerCase(1),
                                new ContainUpperCase(1),
                                new ContainNumber(1),
                                new ContainSpecialCharacter(1)
                        )
                )
        );

        GeneralTextValidator passwordStrengthValidator = new GeneralTextValidator();
        passwordStrengthValidator.addValidationRule(compositePasswordRule);
        validationManager.addField(passwordStrengthEditText, passwordStrengthValidator, Color.parseColor("#8A2BE2"), Color.parseColor("#20B2AA"));

        // valid passwords - P@ssw0rd123, LongPassword123, Str0ng&SecureP@ssword
        // invalid passwords - Sh0rt! (too short), password123! (no uppercase), password! (no number)

        // Custom Palindrome Validator with RuleBuilder
        ValidationRule palindromeRule = new ValidationRule() {
            @Override
            public boolean isValid(String input) {
                String cleaned = input.replaceAll("\\s+", "").toLowerCase();
                return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
            }

            @Override
            public String getErrorMessage() {
                return "Input is not a palindrome";
            }

            @Override
            public int getPriority() {
                return 0;
            }
        };

        ValidationRule compositePalindromeRule = RuleBuilder.and(
                new MinLength(3),
                RuleBuilder.not(new ContainNumber(1)),
                palindromeRule
        );

        GeneralTextValidator palindromeValidator = new GeneralTextValidator();
        palindromeValidator.addValidationRule(compositePalindromeRule);
        validationManager.addField(palindromeEditText, palindromeValidator, Color.parseColor("#FF4500"), Color.parseColor("#00CED1"));

        submitButton.setOnClickListener(v -> {
            if (validationManager.validate()) {
                Toast.makeText(this, "Custom validation successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please fix the errors", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(v -> finish());
    }
}