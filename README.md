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




<button onclick="copyToClipboard('code-snippet')">Copy Code</button>

<script>
function copyToClipboard(id) {
  var code = document.getElementById(id).innerText;
  navigator.clipboard.writeText(code).then(function() {
    console.log('Code copied to clipboard');
  }, function(err) {
    console.error('Error in copying code: ', err);
  });
}
</script>
<pre id="code-snippet">
console.log('Hello, world!');
</pre>
