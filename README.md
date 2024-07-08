# Validator Library
An library who made for Android Studio developers to help you to validate your users inputs

## Validate Options
- Date
- Email
- Max and Min input length
- Number
- Lower and Upper cases
- Contain numbers
- Contain special characters
- Url
- Can make your own validation


## Installation - Gradle

In setting.gradle.kts:
```java
dependencyResolutionManagement {
    repositories {
        ...
        maven("https://jitpack.io")
        ...
    }
}
```

In build.gradle.kts of the app:
```java
dependencies {
    ...
    implementation("com.github.bendayaniv:ValidatorLibrary:1.0.0")
    ...
}
```


## Example of Implementation
In your xml file:
```java
<com.google.android.material.textfield.TextInputLayout
        android:id="@+id/passwordLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <com.example.validator.CustomEditText
            android:id="@+id/passwordEditText"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="@string/password"
            android:inputType="textPassword" />
    </com.google.android.material.textfield.TextInputLayout>
```

In your java file:
- Import the library
```java
import com.example.validator.*;
import com.example.validator.Rules.*;
```

- Find the View
```java
CustomEditText emailEditText = findViewById(R.id.emailEditText);
```
- Create the validator and give him rules
```java
GeneralTextValidator passwordValidator = new GeneralTextValidator();
passwordValidator.addValidationRule(new MinLengthValidationRule(8));
passwordValidator.addValidationRule(new MustContainLowerCaseValidationRule(1));
passwordValidator.addValidationRule(new MustContainUpperCaseValidationRule(1));
passwordValidator.addValidationRule(new MustContainNumberValidationRule(1));
passwordValidator.addValidationRule(new MustContainSpecialCharacterValidationRule(1));
passwordValidator.addValidationRule(new ValidationRule() {
@Override
public boolean isValid(String input) {
    return !input.isEmpty();
}

@Override
public String getErrorMessage() {
    return "This field cannot be empty";
}
        });
```


- Creating ValidationManager and add it to him the validators (GeneralTextValidator) and the CustomEditText
```java
ValidationManager validationManager = new ValidationManager();
validationManager.addField(passwordEditText, passwordValidator);
```



