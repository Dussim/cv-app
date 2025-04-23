# Testing Guidelines for CV App

## Testing Framework

### Kotest
All tests in this project should use [Kotest](https://kotest.io/) as the testing framework. Kotest is a flexible and comprehensive testing framework for Kotlin with multiplatform support.

### FunSpec Style
All tests should use the **FunSpec** style, which provides a simple and readable way to structure tests.

Example:
```kotlin
class MyTest : FunSpec({
    test("my first test") {
        // test code here
    }
    
    test("another test") {
        // more test code
    }
})
```

## Test Structure

### Test Organization
- Group related tests in the same test class
- Use descriptive test names that explain what is being tested
- Use `context` blocks to group related tests when appropriate

### Setup and Teardown
Use the following hooks for setup and teardown:
- `beforeTest` - Runs before each test
- `afterTest` - Runs after each test
- `beforeSpec` - Runs once before any test in the spec
- `afterSpec` - Runs once after all tests in the spec

Example:
```kotlin
class ResourceTest : FunSpec({
    lateinit var tempDir: File
    
    beforeTest {
        tempDir = Files.createTempDirectory("test").toFile()
    }
    
    afterTest {
        tempDir.deleteRecursively()
    }
    
    test("test something") {
        // test code using tempDir
    }
})
```

## Assertions

Use Kotest's assertion library for all assertions:

```kotlin
// Examples
value shouldBe expected
map.shouldContainKey(key)
list.shouldContainExactly(a, b, c)
result.shouldBeEmpty()
```

## Test Coverage

- Aim for high test coverage, especially for business logic
- Test both positive scenarios and edge cases
- Use parameterized tests for testing multiple inputs with the same logic

## Running Tests

Tests can be run from your IDE or using Gradle:

```bash
./gradlew test
```

## Dependencies

Make sure to include the following dependencies in your module's build.gradle.kts file:

```kotlin
    // Kotest
testImplementation(libs.kotest.runner.junit5)
testImplementation(libs.kotest.assertions.core)
testImplementation(libs.kotest.property)

// Coroutines test
testImplementation(libs.kotlinx.coroutines.test)
```

and configure a test task
```kotlin
tasks.test {
    useJUnitPlatform()
}
```