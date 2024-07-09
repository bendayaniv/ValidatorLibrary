package com.example.validatorlibrary;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.validator.*;
import com.example.validator.Rules.*;
import com.example.validator.Rules.Number;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        CustomEditText emailEditText = findViewById(R.id.emailEditText);

        CustomEditText passwordEditText = findViewById(R.id.passwordEditText);

        CustomEditText phoneEditText = findViewById(R.id.phoneEditText);

        CustomEditText nameEditText = findViewById(R.id.nameEditText);

        CustomEditText dateEditText = findViewById(R.id.dateEditText);

        Button submitButton = findViewById(R.id.submitButton);

        ValidationManager validationManager = new ValidationManager();

        // Password validator
        GeneralTextValidator passwordValidator = new GeneralTextValidator();

        ValidationRule notEmptyRule = new ValidationRule() {
            @Override
            public boolean isValid(String input) {
                return !input.isEmpty();
            }

            @Override
            public String getErrorMessage() {
                return "This field cannot be empty";
            }
        };
        passwordValidator.addValidationRule(notEmptyRule);
//        passwordValidator.addValidationRule(new ContainLetter(2));
        passwordValidator.addValidationRule(new MinLength(8));
        passwordValidator.addValidationRule(new MaxLength(10));
        passwordValidator.addValidationRule(new ContainLowerCase(1));
        passwordValidator.addValidationRule(new ContainUpperCase(1));
        passwordValidator.addValidationRule(new ContainNumber(1));
        passwordValidator.addValidationRule(new ContainSpecialCharacter(1));

        validationManager.addField(passwordEditText, passwordValidator, Color.BLUE, Constants.SUCCESS);

        // Email validator
        GeneralTextValidator emailValidator = new GeneralTextValidator();
        emailValidator.addValidationRule(new Email());

        validationManager.addField(emailEditText, emailValidator, Color.BLACK, Constants.ERROR);

        // Phone number validator
        GeneralTextValidator phoneValidator = new GeneralTextValidator();
        phoneValidator.addValidationRule(new MinLength(10));
        phoneValidator.addValidationRule(new MaxLength(10));
        phoneValidator.addValidationRule(new Number());

        validationManager.addField(phoneEditText, phoneValidator);

        // Name validator
        GeneralTextValidator nameValidator = new GeneralTextValidator();
//        nameValidator.addValidationRule(new MinLength(3));
//        nameValidator.addValidationRule(new MaxLength(50));
        nameValidator.addValidationRule(new Name());

        validationManager.addField(nameEditText, nameValidator);

        // Date validator
        GeneralTextValidator dateValidator = new GeneralTextValidator();
        dateValidator.addValidationRule(new Date("dd/mm/yyyy"));

        validationManager.addField(dateEditText, dateValidator);

        submitButton.setOnClickListener(v -> {
            if (validationManager.validate()) {
                Toast.makeText(this, "Form submitted successfully!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Please fix the errors", Toast.LENGTH_LONG).show();
            }
        });

    }
}