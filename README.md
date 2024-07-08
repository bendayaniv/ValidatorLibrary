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
`
dependencyResolutionManagement {
    repositories {
        ...
        maven("https://jitpack.io")
        ...
    }
}
`

In build.gradle.kts of the app:
`
dependencies {
    ...
    implementation("com.github.bendayaniv:ValidatorLibrary:1.0.0")
    ...
}
`
