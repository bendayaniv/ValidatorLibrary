package com.example.validatorlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBasicValidation = findViewById(R.id.btnBasicValidation);
        Button btnAdvancedValidation = findViewById(R.id.btnAdvancedValidation);
        Button btnCustomValidation = findViewById(R.id.btnCustomValidation);

        btnBasicValidation.setOnClickListener(v -> startActivity(new Intent(this, BasicValidationActivity.class)));
        btnAdvancedValidation.setOnClickListener(v -> startActivity(new Intent(this, AdvancedValidationActivity.class)));
        btnCustomValidation.setOnClickListener(v -> startActivity(new Intent(this, CustomValidationActivity.class)));
    }
}