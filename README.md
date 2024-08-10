# 🛡️ Validator Library

Flexible validation library for Android developers to effortlessly validate user inputs with customizable rules, visual feedback, and complex rule compositions.

## ✨ Features

- 📏 Wide range of built-in validation rules
- 🎨 Customizable error and success colors
- 🧩 Easy composition of complex validation logic with RuleBuilder
- 🚀 Automatic real-time input validation
- 💼 Create custom validation rules

## 📦 Installation

Add the following to your `settings.gradle.kts`:

```gradle
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Then, add the dependency to your app's `build.gradle.kts`:

```gradle
dependencies {
    implementation("com.github.bendayaniv:ValidatorLibrary:1.00.03")
}
```

## 🧰 Validation Options

- 🔤 AllLowerCase: `new AllLowerCase()`
- 🔠 AllUpperCase: `new AllUpperCase()`
- 🔤 ContainLetter: `new ContainLetter(int amount)`
- 🔤 ContainLowerCase: `new ContainLowerCase(int amount)`
- 🔢 ContainNumber: `new ContainNumber(int amount)`
- 🔣 ContainSpecialCharacter: `new ContainSpecialCharacter(int amount)`
- 🔠 ContainUpperCase: `new ContainUpperCase(int amount)`
- 📅 Date: `new Date(String dateFormat)`
- ✉️ Email: `new Email()`
- 🏁 EndsWith: `new EndsWith(String suffix)`
- 🔍 GeneralContain: `new GeneralContain(String substring)`
- 🚫 GeneralNotContain: `new GeneralNotContain(String substring)`
- 📏 MaxLength: `new MaxLength(int max)`
- 📏 MinLength: `new MinLength(int min)`
- 👤 Name: `new Name()`
- 🔢 Number: `new Number()`
- 🔢 NumericRange: `new NumericRange(double min, double max)`
- 🏆 PriorityRule: `new PriorityRule(ValidationRule rule, int priority)`
- 🚩 StartsWith: `new StartsWith(String prefix)`
- 🌐 Url: `new Url()`
- ➕ Custom Validation:
  ```java
  new ValidationRule() {
      @Override
      public boolean isValid(String input) {
          return /* your validation logic */;
      }

      @Override
      public String getErrorMessage() {
          return "Custom error message";
      }

      @Override
      public int getPriority() {
          return 0;
      }
  }
  ```

## 🎨 Customizing Colors

Customize error and success colors when adding fields to the ValidationManager:

```java
// Change both colors
validationManager.addField(customEditText, validator, Color.RED, Color.GREEN);

// Change only error color
validationManager.addField(customEditText, validator, Color.RED, Constants.SUCCESS);

// Change only success color
validationManager.addField(customEditText, validator, Color.GREEN, Constants.ERROR);

// Use default colors
validationManager.addField(customEditText, validator);
```

## 💡 Usage Example

XML layout:

```xml
<com.google.android.material.textfield.TextInputLayout
    android:id="@+id/passwordLayout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    <com.example.validator.CustomEditText
        android:id="@+id/passwordEditText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="@string/password"
        android:inputType="textPassword"/>
</com.google.android.material.textfield.TextInputLayout>
```

Java implementation:

```java
import com.example.validator.*;
import com.example.validator.Rules.*;

// Find view
CustomEditText passwordEditText = findViewById(R.id.passwordEditText);

// Create validator
GeneralTextValidator passwordValidator = new GeneralTextValidator();

// Add validation rules
passwordValidator.addValidationRule(new ValidationRule() {
    @Override
    public boolean isValid(String input) {
        return !input.isEmpty();
    }

    @Override
    public String getErrorMessage() {
        return "This field cannot be empty";
    }

    @Override
    public int getPriority() {
        return 0;
    }
});
```
<img src="https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/94d60878-3c69-4e8a-9d99-b927443489dd" width="200" alt="Empty-Filed">

```java
passwordValidator.addValidationRule(new MinLength(8));
```
<img src="https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/cab7e585-92c4-4809-bfce-707e8637c2ff" width="200" alt="At-Least-8-Chars">

```java
passwordValidator.addValidationRule(new MaxLength(10));
```
<img src="https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/1794f6d3-b35c-48e7-a6ad-4561b05034af" width="200" alt="Max-Of-10-Chars">

```java
passwordValidator.addValidationRule(new ContainLowerCase(1));
```
<img src="https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/de18e284-6811-4c7c-9698-ce1f37ec85fe" width="200" alt="At-Least-1-Lower-case">

```java
passwordValidator.addValidationRule(new ContainUpperCase(1));
```
<img src="https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/285c0ec8-5ea4-4b43-b077-f77d41ac23f5" width="200" alt="At-Least-1-Upper-Case">

```java
passwordValidator.addValidationRule(new ContainNumber(1));
```
<img src="https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/a5a5623f-7562-42d8-91fa-30a6663e4ae6" width="200" alt="At-Least-1-Number">

```java
passwordValidator.addValidationRule(new ContainSpecialCharacter(1));
```
<img src="https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/1a55065a-5c3d-4355-8253-70c7626aa4a4" width="200" alt="At-least-1-Special-Char">

```java
// Create ValidationManager and add field
ValidationManager validationManager = new ValidationManager();
validationManager.addField(passwordEditText, passwordValidator);
```
<img src="https://github.com/bendayaniv/ValidatorLibrary/assets/52703125/f91a2661-5cc7-42aa-9866-d58ec2c35382" width="200" alt="Valid">

```java
// Perform validation
if (validationManager.validate()) {
    // Input is valid
} else {
    // Input is invalid
}
```

## 🚀 Advanced Usage: Rule Composition

The Validator Library provides powerful rule composition capabilities using the `RuleBuilder` class and logical operations (AND, OR, NOT). This allows you to create complex validation logic by combining multiple rules.

### RuleBuilder

The `RuleBuilder` class offers methods to compose rules:

- `RuleBuilder.and(ValidationRule... rules)`: Combines rules with AND logic
- `RuleBuilder.or(ValidationRule... rules)`: Combines rules with OR logic
- `RuleBuilder.not(ValidationRule rule)`: Negates a rule
- `RuleBuilder.withPriority(ValidationRule rule, int priority)`: Assigns priority to a rule

### Example of Complex Rule Composition

```java
ValidationRule complexRule = RuleBuilder.and(
    RuleBuilder.withPriority(new MinLength(8), 2),  // Higher priority
    RuleBuilder.or(
        RuleBuilder.and(
            new ContainLowerCase(1),
            new ContainUpperCase(1),
            new ContainNumber(1)
        ),
        RuleBuilder.withPriority(new ContainSpecialCharacter(2), 1)  // Medium priority
    ),
    RuleBuilder.not(new GeneralContain("password"))
);

passwordValidator.addValidationRule(complexRule);
```

This complex rule ensures that the password:
1. Is at least 8 characters long (high priority)
2. Either contains at least one lowercase, one uppercase, and one number, OR contains at least two special characters (special character rule has medium priority)
3. Does not contain the word "password"

The use of `withPriority` in this example means that:
- The minimum length requirement will be checked first and its error message (if any) will be displayed with highest priority.
- If the special character rule fails, its error message will be shown before the lowercase/uppercase/number combination, but after the length requirement.

By using rule composition and priorities, you can create sophisticated validation logic tailored to your specific requirements and control the order in which error messages are displayed to the user.

## 📱 Example App

This repository includes an example Android app that demonstrates the usage of the Validator Library. The app showcases various validation scenarios and how to implement them using the library. You can find the example app in the `app` directory of this repository. It's a great resource to see the library in action and to learn how to integrate it into your own projects.
