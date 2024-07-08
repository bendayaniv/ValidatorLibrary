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

- Find View
```java
CustomEditText passwordEditText = findViewById(R.id.passwordEditText);
```
- Create the validator and 
```java
GeneralTextValidator passwordValidator = new GeneralTextValidator();
```

- Give the validator rules
```java
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
```
![Empty-Filed](https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/94d60878-3c69-4e8a-9d99-b927443489dd)

```java
passwordValidator.addValidationRule(new MinLengthValidationRule(8));
```
![At-Least-8-Chars](https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/cab7e585-92c4-4809-bfce-707e8637c2ff)

```java
passwordValidator.addValidationRule(new MaxLengthValidationRule(10));
```
![Max-Of-10-Chars](https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/1794f6d3-b35c-48e7-a6ad-4561b05034af)


```java
passwordValidator.addValidationRule(new MustContainLowerCaseValidationRule(1));
```
![At-Least-1-Lower-case](https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/de18e284-6811-4c7c-9698-ce1f37ec85fe)

```java
passwordValidator.addValidationRule(new MustContainUpperCaseValidationRule(1));
```
![At-Least-1-Upper-Case](https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/285c0ec8-5ea4-4b43-b077-f77d41ac23f5)

```java
passwordValidator.addValidationRule(new MustContainNumberValidationRule(1));
```
![At-Least-1-Number](https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/a5a5623f-7562-42d8-91fa-30a6663e4ae6)

```java
passwordValidator.addValidationRule(new MustContainSpecialCharacterValidationRule(1));
```
![At-least-1-Special-Char](https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/1a55065a-5c3d-4355-8253-70c7626aa4a4)


- Creating ValidationManager and add to him the validators (GeneralTextValidator) and the CustomEditText
```java
ValidationManager validationManager = new ValidationManager();
validationManager.addField(passwordEditText, passwordValidator);
```

![Valid](https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/f91a2661-5cc7-42aa-9866-d58ec2c35382)




