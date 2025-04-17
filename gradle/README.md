# Gradle Version Catalog

This project uses Gradle Version Catalog to manage dependencies and plugins. The version catalog is defined in the `libs.versions.toml` file.

## Structure

The version catalog is organized into the following sections:

1. `[versions]` - Contains all version numbers
2. `[libraries]` - Contains all library definitions, grouped by category
3. `[plugins]` - Contains all plugin definitions
4. `[bundles]` - Contains bundles of related libraries for convenience

## How to Use

### In Module Build Files

To use the version catalog in your module build files, replace direct dependency declarations with references to the version catalog:

```kotlin
// Before
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

// After
implementation(libs.kotlinx.coroutines.core)
implementation(libs.kotlinx.coroutines.android)

// Or using bundles
implementation(libs.bundles.kotlinx.coroutines)
```

### For Plugins

To use plugins from the version catalog:

```kotlin
// Before
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

// After
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}
```

## Available Dependencies

The version catalog includes the following categories of dependencies:

- Kotlin and KotlinX
- AndroidX
- Compose
- Testing (Kotest)
- Build tools
- Navigation (Voyager)
- Google services

## Available Bundles

For convenience, the following bundles are available:

- `kotlinx.coroutines` - Includes core and Android coroutines libraries
- `kotest` - Includes all Kotest libraries
- `compose.ui` - Includes basic Compose UI libraries
- `voyager` - Includes all Voyager navigation libraries

## Updating Dependencies

To add a new dependency:

1. Add the version to the `[versions]` section if it's not already there
2. Add the library definition to the `[libraries]` section
3. Optionally, add it to a bundle in the `[bundles]` section

Example:

```toml
[versions]
some-library = "1.0.0"

[libraries]
some-library = { group = "com.example", name = "some-library", version.ref = "some-library" }

[bundles]
some-bundle = ["some-library", "another-library"]
```