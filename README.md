# Validator Library
An library who made for Android Studio developers to help you to validate your users inputs

## Content
*[Validate Options](#validate-options)
*[Change Error and Success Colors](#change-error-and-success-colors)
*[Installation - Gradle](#installation---gradle)
*[Example of Implementation](#example-of-implementation)


## Validate Options
- Date
```java
new Date(String dateFormat)
```
- Email
```java
new Email()
```
- Max input length
```java
new MinLength(int min)
```
- Min input length
```java
new MaxLength(int max)
```
- Number
```java
new Number()
```
- Lower case
```java
new ContainLowerCase(int amount)
```
- Upper case
```java
new ContainUpperCase(int amount)
```
- Contain numbers
```java
new ContainNumber(int amount)
```
- Contain special characters
```java
new ContainSpecialCharacter(int amount)
```
- Url
```java
new Url()
```
- Letters
```java
new Letters()
```
- Can make your own validation
```java
new ValidationRule() {
    @Override
    public boolean isValid(String input) {
        return the_rule;
    }

    @Override
    public String getErrorMessage() {
        return the_message;
    }
};
```

## Change Error and Success Colors

You can change colors when adding new field into the ValidationManager (the default colors are - red (0xFFFF0000) for error and green (0xFF00FF00) for success)
- Changing both colors
```java
validationManager.addField(CustomEditText, GeneralTextValidator, error_color(example - Color.BLACK), success_color(exampel - Color.BLUE));
```
- Changing only the error color
```java
validationManager.addField(CustomEditText, GeneralTextValidator, error_color(example - Color.BLACK), Constants.ERROR);
```
- Changing only the success color
```java
validationManager.addField(CustomEditText, GeneralTextValidator, success_color(exampel - Color.BLUE), Constants.SUCCESS);
```
- Using the default colors
```java
validationManager.addField(CustomEditText, GeneralTextValidator);
```


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
    implementation("com.github.bendayaniv:ValidatorLibrary:1.0.1")
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
passwordValidator.addValidationRule(new MinLength(8));
```
![At-Least-8-Chars](https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/cab7e585-92c4-4809-bfce-707e8637c2ff)

```java
passwordValidator.addValidationRule(new MaxLength(10));
```
![Max-Of-10-Chars](https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/1794f6d3-b35c-48e7-a6ad-4561b05034af)


```java
passwordValidator.addValidationRule(new ContainLowerCase(1));
```
![At-Least-1-Lower-case](https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/de18e284-6811-4c7c-9698-ce1f37ec85fe)

```java
passwordValidator.addValidationRule(new ContainUpperCase(1));
```
![At-Least-1-Upper-Case](https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/285c0ec8-5ea4-4b43-b077-f77d41ac23f5)

```java
passwordValidator.addValidationRule(new ContainNumber(1));
```
![At-Least-1-Number](https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/a5a5623f-7562-42d8-91fa-30a6663e4ae6)

```java
passwordValidator.addValidationRule(new ContainSpecialCharacter(1));
```
![At-least-1-Special-Char](https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/1a55065a-5c3d-4355-8253-70c7626aa4a4)


- Creating ValidationManager
```java
ValidationManager validationManager = new ValidationManager();
```
- Add to the ValidationManager the CustomEditText and the validators (GeneralTextValidator)
```java
validationManager.addField(passwordEditText, passwordValidator);
```

![Valid](https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/f91a2661-5cc7-42aa-9866-d58ec2c35382)




