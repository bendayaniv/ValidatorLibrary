package com.example.validatorlibrary;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.validator.*;
import com.example.validator.Rules.*;
import com.google.android.material.textfield.TextInputLayout;

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

        TextInputLayout emailLayout = findViewById(R.id.emailLayout);
        CustomEditText emailEditText = findViewById(R.id.emailEditText);

        TextInputLayout passwordLayout = findViewById(R.id.passwordLayout);
        CustomEditText passwordEditText = findViewById(R.id.passwordEditText);

        TextInputLayout phoneLayout = findViewById(R.id.phoneLayout);
        CustomEditText phoneEditText = findViewById(R.id.phoneEditText);

        TextInputLayout generalLayout = findViewById(R.id.generalLayout);
        CustomEditText nameEditText = findViewById(R.id.nameEditText);

        TextInputLayout dateLayout = findViewById(R.id.dateLayout);
        CustomEditText dateEditText = findViewById(R.id.dateEditText);

        Button submitButton = findViewById(R.id.submitButton);

        ValidationManager validationManager = new ValidationManager();

        // Password validator
        GeneralTextValidator passwordValidator = new GeneralTextValidator();
        passwordValidator.addValidationRule(new MinLengthValidationRule(8));
        passwordValidator.addValidationRule(new MustContainLowerCaseValidationRule(1));
        passwordValidator.addValidationRule(new MustContainUpperCaseValidationRule(1));
        passwordValidator.addValidationRule(new MustContainNumberValidationRule(1));
        passwordValidator.addValidationRule(new MustContainSpecialCharacterValidationRule(1));

        validationManager.addField(passwordEditText, passwordValidator);

        // Email validator
        GeneralTextValidator emailValidator = new GeneralTextValidator();
        emailValidator.addValidationRule(new EmailValidationRule());

        validationManager.addField(emailEditText, emailValidator);

        // Phone number validator
        GeneralTextValidator phoneValidator = new GeneralTextValidator();
        phoneValidator.addValidationRule(new MinLengthValidationRule(10));
        phoneValidator.addValidationRule(new MaxLengthValidationRule(10));
        phoneValidator.addValidationRule(new MustBeNumberValidationRule());

        validationManager.addField(phoneEditText, phoneValidator);

        // Name validator
        GeneralTextValidator nameValidator = new GeneralTextValidator();
        nameValidator.addValidationRule(new MinLengthValidationRule(3));
        nameValidator.addValidationRule(new MaxLengthValidationRule(50));

        validationManager.addField(nameEditText, nameValidator);

        // Date validator
        GeneralTextValidator dateValidator = new GeneralTextValidator();
        dateValidator.addValidationRule(new DateValidationRule("dd/mm/yyyy"));

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