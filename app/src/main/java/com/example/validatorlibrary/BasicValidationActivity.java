package com.example.validatorlibrary;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.validator.*;
import com.example.validator.Rules.*;

public class BasicValidationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_validation);

        CustomEditText emailEditText = findViewById(R.id.emailEditText);
        CustomEditText nameEditText = findViewById(R.id.nameEditText);
        CustomEditText dateEditText = findViewById(R.id.dateEditText);
        CustomEditText urlEditText = findViewById(R.id.urlEditText);
        Button submitButton = findViewById(R.id.submitButton);
        Button backButton = findViewById(R.id.backButton);

        ValidationManager validationManager = new ValidationManager();

        // Email validator with custom error color (Purple)
        GeneralTextValidator emailValidator = new GeneralTextValidator();
        emailValidator.addValidationRule(new Email());
        validationManager.addField(emailEditText, emailValidator, Color.parseColor("#8E44AD"), Constants.SUCCESS);
        // Valid email: user@example.com
        // Invalid email: user@example (missing top-level domain)

        // Name validator with custom success color (Orange)
        GeneralTextValidator nameValidator = new GeneralTextValidator();
        nameValidator.addValidationRule(new Name());
        validationManager.addField(nameEditText, nameValidator, Color.parseColor("#F39C12"), Constants.ERROR);
        // Valid name: John Doe
        // Invalid name: John123 (contains numbers)

        // Date validator with custom colors (Teal for error, Pink for success)
        GeneralTextValidator dateValidator = new GeneralTextValidator();
        dateValidator.addValidationRule(new Date("dd/MM/yyyy"));
        validationManager.addField(dateEditText, dateValidator, Color.parseColor("#16A085"), Color.parseColor("#E91E63"));
        // Valid date: 25/12/2023
        // Invalid date: 30/02/2023 (February 30th doesn't exist)

        // URL validator with default colors
        GeneralTextValidator urlValidator = new GeneralTextValidator();
        urlValidator.addValidationRule(new Url());
        validationManager.addField(urlEditText, urlValidator);
        // Valid URL: https://www.example.com
        // Invalid URL: www.example (missing protocol)

        submitButton.setOnClickListener(v -> {
            if (validationManager.validate()) {
                Toast.makeText(this, "Basic validation successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please fix the errors", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(v -> finish());
    }
}