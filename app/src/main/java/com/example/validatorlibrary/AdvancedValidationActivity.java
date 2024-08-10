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

public class AdvancedValidationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_validation);

        CustomEditText passwordEditText = findViewById(R.id.passwordEditText);
        CustomEditText phoneEditText = findViewById(R.id.phoneEditText);
        CustomEditText customEditText = findViewById(R.id.customEditText);
        Button submitButton = findViewById(R.id.submitButton);
        Button backButton = findViewById(R.id.backButton);

        ValidationManager validationManager = new ValidationManager();

        // Password validator with composition, priorities, and custom colors
        GeneralTextValidator passwordValidator = new GeneralTextValidator();
        ValidationRule passwordRule = RuleBuilder.and(
                RuleBuilder.withPriority(new MinLength(8), 3), // Highest priority
                RuleBuilder.withPriority(new MaxLength(20), 2), // Medium priority
                RuleBuilder.or(
                        RuleBuilder.and(
                                new ContainLowerCase(1),
                                new ContainUpperCase(1),
                                new ContainNumber(1)
                        ),
                        RuleBuilder.withPriority(new ContainSpecialCharacter(2), 1) // Lower priority
                ),
                RuleBuilder.not(new GeneralContain("password"))
        );
        passwordValidator.addValidationRule(passwordRule);
        validationManager.addField(passwordEditText, passwordValidator, Color.parseColor("#FF6B6B"), Color.parseColor("#4ECDC4"));
        // Valid password: Str0ngP@ss
        // Invalid password: weakpass (too short, no uppercase, no number, no special chars)
        // Invalid password with priority: pass (MinLength error will be shown first)
        // Invalid password with priority: VeryLongPasswordWithoutSpecialChars123 (MaxLength error will be shown before special char requirement)

        // Phone number validator with custom error color
        GeneralTextValidator phoneValidator = new GeneralTextValidator();
        phoneValidator.addValidationRule(new Number());
        phoneValidator.addValidationRule(new MinLength(10));
        phoneValidator.addValidationRule(new MaxLength(10));
        validationManager.addField(phoneEditText, phoneValidator, Color.parseColor("#F7B731"), Constants.SUCCESS);
        // Valid phone: 1234567890
        // Invalid phone: 123-456-7890 (contains non-numeric characters)

        // Custom validator
        GeneralTextValidator customValidator = new GeneralTextValidator();
        customValidator.addValidationRule(RuleBuilder.and(
                new GeneralContain("example"),
                RuleBuilder.not(new EndsWith("test")),
                new StartsWith("demo")
        ));
        validationManager.addField(customEditText, customValidator, Color.parseColor("#5D5D5D"), Color.parseColor("#95A5A6"));
        // Valid input: demo-example-content
        // Invalid input: demo-content-test (ends with "test")

        submitButton.setOnClickListener(v -> {
            if (validationManager.validate()) {
                Toast.makeText(this, "Advanced validation successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please fix the errors", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(v -> finish());
    }
}