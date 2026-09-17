# Project State & Context Documentation

This document serves as the absolute state-of-the-project summary for future AI assistants. Whenever a new AI begins working, they can read this file to understand precisely what has been implemented, how to build/compile, and where to pick up the execution.

---

## 1. Project Overview & Tech Stack
- **Project Name:** Campus Lost & Found
- **Platform:** Native Android (Kotlin + Jetpack Compose + Material 3)
- **Database / Backend:** Supabase (PostgreSQL, Auth, Storage, Realtime)
- **Architecture Pattern:** MVVM (Clean Architecture layers: Data, Domain, Presentation/UI)

---

## 2. Completed Tasks Summary

### Task 1.1 — Create native Android project scaffold
Implemented a fully functional Android project scaffold inside the [`android/`](android) directory.
- **Created/Modified:**
  - [`android/build.gradle.kts`](android/build.gradle.kts)
  - [`android/settings.gradle.kts`](android/settings.gradle.kts)
  - [`android/gradle.properties`](android/gradle.properties)
  - [`android/gradlew`](android/gradlew)
  - [`android/gradle/wrapper/gradle-wrapper.properties`](android/gradle/wrapper/gradle-wrapper.properties)
  - [`android/app/build.gradle.kts`](android/app/build.gradle.kts)
  - [`android/app/src/main/AndroidManifest.xml`](android/app/src/main/AndroidManifest.xml)
- **Implementation:**
  - Established a standard multi-project Gradle setup for a native Android application with an `app` module.
- **Verification:**
  - Compiles successfully using Gradle Wrapper `./gradlew`.

### Task 1.2 — Configure Kotlin and Jetpack Compose
Implemented Kotlin and Jetpack Compose compiler support and configuration.
- **Created/Modified:**
  - [`android/app/build.gradle.kts`](android/app/build.gradle.kts)
  - [`android/gradle/libs.versions.toml`](android/gradle/libs.versions.toml)
- **Implementation:**
  - Configured Kotlin plugin (`org.jetbrains.kotlin.android`) version `2.0.21` and Jetpack Compose compiler plugin (`org.jetbrains.kotlin.plugin.compose`) inside [`android/app/build.gradle.kts`](android/app/build.gradle.kts) and project-level [`android/build.gradle.kts`](android/build.gradle.kts).
  - Enabled Compose build feature (`buildFeatures { compose = true }`) and imported Compose Bill of Materials (BOM) (`androidx-compose-bom`).
- **Verification:**
  - Clean Kotlin compilation via `./gradlew compileDebugKotlin` verifies correct configuration of Jetpack Compose.

### Task 1.3 — Configure Material 3
Implemented and configured the Google Material 3 Design System dependencies and theme foundation.
- **Created/Modified:**
  - [`android/app/build.gradle.kts`](android/app/build.gradle.kts)
  - [`android/gradle/libs.versions.toml`](android/gradle/libs.versions.toml)
- **Implementation:**
  - Added dependency support for `androidx.compose.material3` inside [`android/gradle/libs.versions.toml`](android/gradle/libs.versions.toml) and [`android/app/build.gradle.kts`](android/app/build.gradle.kts).
- **Verification:**
  - Successfully imported and referenced inside Compose UI files.

### Task 1.4 — Create base package/folder structure
Created the main root package structure for the project.
- **Created/Modified:**
  - [`android/app/src/main/java/com/example/campuslostfound/MainActivity.kt`](android/app/src/main/java/com/example/campuslostfound/MainActivity.kt)
- **Implementation:**
  - Established the `com.example.campuslostfound` base package containing the project's entry point `MainActivity`.
- **Verification:**
  - Package matches namespace configuration `com.example.campuslostfound` in [`android/app/build.gradle.kts`](android/app/build.gradle.kts).

### Task 1.5 — Configure Gradle dependencies
Configured centralized and modular Gradle dependency management.
- **Created/Modified:**
  - [`android/gradle/libs.versions.toml`](android/gradle/libs.versions.toml)
  - [`android/build.gradle.kts`](android/build.gradle.kts)
  - [`android/app/build.gradle.kts`](android/app/build.gradle.kts)
- **Implementation:**
  - Utilized Gradle Version Catalogs ([`libs.versions.toml`](android/gradle/libs.versions.toml)) to declare and share plugins and library dependencies across the Android project.
- **Verification:**
  - Successfully resolves and syncs all dependencies with Gradle.

### Task 1.6 — Configure minimum/target Android SDK
Configured target and minimum operating system specifications.
- **Created/Modified:**
  - [`android/app/build.gradle.kts`](android/app/build.gradle.kts)
- **Implementation:**
  - Defined `minSdk = 24` (Android 7.0 Nougat) and `targetSdk = 36` (Android 15 Q) within the `defaultConfig` block.
- **Verification:**
  - Build files correctly parse compile SDK and target SDK settings.

### Task 1.7 — Configure Git repository and `.gitignore`
Configured the source control files to prevent tracking compiled resources and IDE files.
- **Created/Modified:**
  - [`.gitignore`](.gitignore)
  - [`android/.gitignore`](android/.gitignore)
- **Implementation:**
  - Configured global root-level and Android-specific `.gitignore` rules to ignore build files, local caches, IDE configuration (`.idea/`), local properties (`local.properties`), and OS artifacts (`.DS_Store`).
- **Verification:**
  - Untracked artifacts are ignored by Git commands.

### Task 1.8 — Verify clean project build
Verified clean project builds and compilation tasks without issues.
- **Created/Modified:**
  - [`android/gradlew`](android/gradlew)
- **Implementation:**
  - Executed Gradle compile tasks using Android Studio embedded JBR.
- **Verification:**
  - Clean project builds and passes kotlin compilation successfully.

### Task 2.1 — Define app color palette
Implemented a cohesive Material 3 color palette for light and dark themes.
- **Created/Modified:**
  - [`android/app/src/main/java/com/example/campuslostfound/ui/theme/Color.kt`](android/app/src/main/java/com/example/campuslostfound/ui/theme/Color.kt)
- **Implementation:**
  - Defined brand colors representing a deep navy blue primary (`PrimaryLight` / `PrimaryDark`), warm amber secondary (`SecondaryLight` / `SecondaryDark`), and emerald green tertiary (`TertiaryLight` / `TertiaryDark`).
  - Added domain-specific semantic status colors: `StatusLost` (Orange), `StatusFound` (Green), `StatusClaimed` (Gray), and `StatusPending` (Purple).
- **Verification:**
  - Used programmatically throughout the application UI and custom components.

### Task 2.2 — Define typography
Implemented a structured hierarchical typography configuration based on Material 3 specifications.
- **Created/Modified:**
  - [`android/app/src/main/java/com/example/campuslostfound/ui/theme/Type.kt`](android/app/src/main/java/com/example/campuslostfound/ui/theme/Type.kt)
- **Implementation:**
  - Defined responsive and proportional size styles (`display`, `headline`, `title`, `body`, and `label`) using pixel-independent `sp` units and line heights.
- **Verification:**
  - Handled cleanly via `MaterialTheme.typography` in all Compose texts.

### Task 2.3 — Configure application font
Configured the global application font family.
- **Created/Modified:**
  - [`android/app/src/main/java/com/example/campuslostfound/ui/theme/Type.kt`](android/app/src/main/java/com/example/campuslostfound/ui/theme/Type.kt)
- **Implementation:**
  - Established a central `AppFontFamily` token set to `FontFamily.SansSerif` to support rapid customization and system font fallback.
- **Verification:**
  - Font family is applied directly inside all defined `TextStyle` instances.

### Task 2.4 — Configure Material 3 theme
Configured light and dark Material 3 color schemes and theme overlays.
- **Created/Modified:**
  - [`android/app/src/main/java/com/example/campuslostfound/ui/theme/Theme.kt`](android/app/src/main/java/com/example/campuslostfound/ui/theme/Theme.kt)
- **Implementation:**
  - Implemented the `CampusLostFoundTheme` composable function using `darkColorScheme` and `lightColorScheme`.
  - Configured dynamic status bar coloring and edge-to-edge window insets controller behavior.
- **Verification:**
  - Wraps the root of `MainActivity` and enables dark mode toggle previews.

### Task 2.5 — Define spacing and sizing constants
Implemented centralized layout dimension and margin spacing tokens.
- **Created/Modified:**
  - [`android/app/src/main/java/com/example/campuslostfound/ui/theme/Spacing.kt`](android/app/src/main/java/com/example/campuslostfound/ui/theme/Spacing.kt)
- **Implementation:**
  - Created a custom `Spacing` data class containing spacing tokens (`none`, `extraSmall`, `small`, `mediumSmall`, `medium`, `large`, `extraLarge`, `huge`).
  - Added a `Dimensions` object declaring standard icon sizes, button heights (`48.dp`), item cards (`120.dp`), and elevation tokens.
  - Linked the spacing properties via Compose's `staticCompositionLocalOf` to expose `MaterialTheme.spacing`.
- **Verification:**
  - Referenced in composable components to enforce uniform grid and touch targets.

### Task 2.6 — Define reusable shape/radius tokens
Implemented geometric shape and corner radius tokens to style components.
- **Created/Modified:**
  - [`android/app/src/main/java/com/example/campuslostfound/ui/theme/Shape.kt`](android/app/src/main/java/com/example/campuslostfound/ui/theme/Shape.kt)
- **Implementation:**
  - Configured standard Material 3 `Shapes` (extraSmall to extraLarge) using `RoundedCornerShape` values.
  - Created a `Radius` object declaring raw `dp` values from `4.dp` to `28.dp` and `999.dp` for fully circular components.
- **Verification:**
  - Referenced in borders and backgrounds of buttons, cards, and input fields.

### Task 2.7 — Define reusable UI components
Implemented a set of core interactive UI components.
- **Created/Modified:**
  - [`android/app/src/main/java/com/example/campuslostfound/ui/components/CommonComponents.kt`](android/app/src/main/java/com/example/campuslostfound/ui/components/CommonComponents.kt)
- **Implementation:**
  - `PrimaryButton` and `SecondaryButton`: Custom styled Material buttons wrapping clicks with predefined heights and shape tokens.
  - `AppTextField`: Outlined text field wrapper with a unified corner radius and labeled placeholders.
  - `StatusBadge` and `ItemStatus`: High-fidelity semantic status pill displaying "Lost", "Found", "Claimed", or "Pending" with matched backgrounds, texts, and alphas.
- **Verification:**
  - Integrated into functional layouts like `HomeScreen` to build forms and card badges.

### Task 2.8 — Create app icon
Created placeholder app launcher assets.
- **Created/Modified:**
  - [`android/app/src/main/res/drawable/ic_launcher_background.xml`](android/app/src/main/res/drawable/ic_launcher_background.xml)
  - [`android/app/src/main/res/drawable/ic_launcher_foreground.xml`](android/app/src/main/res/drawable/ic_launcher_foreground.xml)
  - [`android/app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml`](android/app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml)
- **Implementation:**
  - Designed vector assets for background and foreground layers, and declared launcher configurations inside `mipmap` directories for adaptive icons.
- **Verification:**
  - Configured as target launcher icons in [`android/app/src/main/AndroidManifest.xml`](android/app/src/main/AndroidManifest.xml).

### Task 2.9 — Create reusable loading component
Implemented robust, reusable UI indicators for asynchronous loading states.
- **Created/Modified:**
  - [`android/app/src/main/java/com/example/campuslostfound/ui/components/LoadingComponent.kt`](android/app/src/main/java/com/example/campuslostfound/ui/components/LoadingComponent.kt)
- **Implementation:**
  - `LoadingIndicator`: Centered circular progress indicator referencing sizing tokens.
  - `LoadingScreen`: Multi-purpose full-screen view containing indicator, spacing, and message.
  - `LoadingOverlay`: Semi-transparent full-screen overlay to block user interactions during transactions.
- **Verification:**
  - Utilized in `HomeScreen` during state emission checks.

### Task 2.10 — Create reusable error component
Implemented user-friendly failure feedback components.
- **Created/Modified:**
  - [`android/app/src/main/java/com/example/campuslostfound/ui/components/ErrorComponent.kt`](android/app/src/main/java/com/example/campuslostfound/ui/components/ErrorComponent.kt)
- **Implementation:**
  - `ErrorScreen`: Full-screen warning view with an action button linking to a retry lambda callback.
  - `ErrorInline`: Compact error banner designed for in-form warnings with compact retry controls.
- **Verification:**
  - Error layouts render automatically upon catching `UiState.Error` emissions.

### Task 2.11 — Create reusable empty-state component
Implemented clean empty search or item list empty-state views.
- **Created/Modified:**
  - [`android/app/src/main/java/com/example/campuslostfound/ui/components/EmptyStateComponent.kt`](android/app/src/main/java/com/example/campuslostfound/ui/components/EmptyStateComponent.kt)
- **Implementation:**
  - `EmptyState`: Renders a centered info vector icon, custom title, message body, and an optional button to trigger actions (e.g. creating a report).
- **Verification:**
  - Validated visually inside composable previews.

### Task 3.1 — Create MVVM Package Structure
Created the presentation-layer package structure under `com.example.campuslostfound.presentation` for future screens and ViewModels. Shared presentation components reside in the `common` subpackage.
- **Created Packages & Files:**
  - [`android/app/src/main/java/com/example/campuslostfound/presentation/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/presentation/.gitkeep) (presentation layer placeholder)
  - [`android/app/src/main/java/com/example/campuslostfound/presentation/common/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/presentation/common/.gitkeep) (shared presentation layer placeholder)

### Task 3.2 — Create Navigation Package
Created the navigation-layer package structure under `com.example.campuslostfound.navigation` to organize routes and navigation hosts.
- **Created/Modified:**
  - [`android/app/src/main/java/com/example/campuslostfound/navigation/AppNavigation.kt`](android/app/src/main/java/com/example/campuslostfound/navigation/AppNavigation.kt)
- **Implementation:**
  - Configured `Screen` sealed class representing distinct app screens (`Login`, `Register`, `Home`, `LostItems`, `FoundItems`, `ItemDetails`, `Profile`) and corresponding route paths.
  - Designed `AppNavigation` host composable wrapping Compose `NavHost` with navigation composable pathways.
- **Verification:**
  - Linked directly within `MainActivity` to orchestrate navigation flows.

### Task 3.3 — Create Shared/Common Package
Created the shared/common package structure under `com.example.campuslostfound.common` to hold reusable, application-wide components, models, constants, and utilities. Since empty directories are ignored by Git, `.gitkeep` placeholder files were utilized to preserve directories.
- **Created Packages & Files:**
  - [`android/app/src/main/java/com/example/campuslostfound/common/components/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/common/components/.gitkeep) (reusable UI components)
  - [`android/app/src/main/java/com/example/campuslostfound/common/model/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/common/model/.gitkeep) (shared models)
  - [`android/app/src/main/java/com/example/campuslostfound/common/util/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/common/util/.gitkeep) (general helpers)
  - [`android/app/src/main/java/com/example/campuslostfound/common/constants/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/common/constants/.gitkeep) (app-wide constants)

### Task 3.4 — Create Data Package
Created the data-layer package structure under `com.example.campuslostfound.data` for repository implementations, models/DTOs, and data sources.
- **Created Packages & Files:**
  - [`android/app/src/main/java/com/example/campuslostfound/data/model/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/data/model/.gitkeep) (DTOs/Models)
  - [`android/app/src/main/java/com/example/campuslostfound/data/repository/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/data/repository/.gitkeep) (Repository implementations)
  - [`android/app/src/main/java/com/example/campuslostfound/data/remote/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/data/remote/.gitkeep) (API/Network)
  - [`android/app/src/main/java/com/example/campuslostfound/data/local/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/data/local/.gitkeep) (Database/Storage)

### Task 3.5 — Create Domain Package
Created the domain-layer package structure under `com.example.campuslostfound.domain` to enforce clean architecture and keep core business logic decoupled from frameworks.
- **Created Packages & Files:**
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/domain/model/.gitkeep) (Domain models)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/repository/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/domain/repository/.gitkeep) (Repository interfaces)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/usecase/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/domain/usecase/.gitkeep) (Use cases)

### Task 3.6 — Create UI/Feature Packages
Created presentation/UI feature package structures under `com.example.campuslostfound.ui` to isolate features.
- **Created Packages & Files:**
  - [`android/app/src/main/java/com/example/campuslostfound/ui/auth/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/ui/auth/.gitkeep) (Authentication UI)
  - [`android/app/src/main/java/com/example/campuslostfound/ui/home/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/ui/home/.gitkeep) (Dashboard UI)
  - [`android/app/src/main/java/com/example/campuslostfound/ui/lostitems/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/ui/lostitems/.gitkeep) (Lost-item listing UI)
  - [`android/app/src/main/java/com/example/campuslostfound/ui/founditems/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/ui/founditems/.gitkeep) (Found-item listing UI)
  - [`android/app/src/main/java/com/example/campuslostfound/ui/itemdetails/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/ui/itemdetails/.gitkeep) (Item Details UI)
  - [`android/app/src/main/java/com/example/campuslostfound/ui/report/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/ui/report/.gitkeep) (Report submission UI)
  - [`android/app/src/main/java/com/example/campuslostfound/ui/profile/.gitkeep`](android/app/src/main/java/com/example/campuslostfound/ui/profile/.gitkeep) (User Profile UI)

### Task 3.7 — Configure ViewModel Architecture
Configured the foundation for the application's ViewModel layer under `com.example.campuslostfound.viewmodel`.
- **Verified & Configured Dependencies:** Added standard Jetpack Compose and AndroidX lifecycle/ViewModel dependencies `lifecycle-viewmodel-ktx` and `lifecycle-viewmodel-compose` inside [`android/gradle/libs.versions.toml`](android/gradle/libs.versions.toml) and [`android/app/build.gradle.kts`](android/app/build.gradle.kts).
- **Created Packages & Files:**
  - [`android/app/src/main/java/com/example/campuslostfound/viewmodel/BaseViewModel.kt`](android/app/src/main/java/com/example/campuslostfound/viewmodel/BaseViewModel.kt) (Architectural base ViewModel establishing standard rules and structure for future UI states, lifecycle-awareness, and repository communication)

### Task 3.8 — Configure StateFlow UI state pattern
Configured a clean and reusable StateFlow-based UI state pattern for future ViewModels.
- **Created Packages & Files:**
  - [`android/app/src/main/java/com/example/campuslostfound/common/state/UiState.kt`](android/app/src/main/java/com/example/campuslostfound/common/state/UiState.kt) (Architectural reusable sealed interface representing Loading, Success, and Error UI states)

### Task 3.9 — Configure Dependency Injection (Hilt)
Established the Dependency Injection (DI) infrastructure using **Dagger Hilt** and integrated Kotlin Annotation Processing (Kapt).
- **Key Technical Configurations Added:**
  - Configured Kotlin Gradle Plugin, Hilt Android, and Kapt plugins inside [`android/app/build.gradle.kts`](android/app/build.gradle.kts) and project-level [`android/build.gradle.kts`](android/build.gradle.kts).
  - Resolved AGP 9.0 default experimental built-in Kotlin compilation conflicts by setting `android.builtInKotlin=false` and `android.newDsl=false` in [`android/gradle.properties`](android/gradle.properties).
  - Configured `kotlinOptions { jvmTarget = "11" }` in [`android/app/build.gradle.kts`](android/app/build.gradle.kts) to resolve JVM target mismatch conflicts.
  - Bumped Hilt compiler and runtime to version `2.55` in [`android/gradle/libs.versions.toml`](android/gradle/libs.versions.toml) to fully support Kotlin 2.0.21 metadata (`2.2.0`) without compiler exceptions.
- **Created Packages & Files:**
  - [`android/app/src/main/java/com/example/campuslostfound/CampusLostFoundApp.kt`](android/app/src/main/java/com/example/campuslostfound/CampusLostFoundApp.kt) — application class annotated with `@HiltAndroidApp` and configured inside [`android/app/src/main/AndroidManifest.xml`](android/app/src/main/AndroidManifest.xml:5).
  - [`android/app/src/main/java/com/example/campuslostfound/di/AppModule.kt`](android/app/src/main/java/com/example/campuslostfound/di/AppModule.kt) — establishes root singleton module injecting context-dependent dependencies.
  - Annotated [`android/app/src/main/java/com/example/campuslostfound/MainActivity.kt`](android/app/src/main/java/com/example/campuslostfound/MainActivity.kt:20) with `@AndroidEntryPoint` to host future injectable dependencies.

### Task 3.10 — Verify Architecture with a Sample Screen
Verified the correctness and integration of the entire MVVM architecture, state patterns, dependency injection, and UI components using a live reactive screen.
- **Implemented Technical Features:**
  - Created [`android/app/src/main/java/com/example/campuslostfound/ui/home/HomeViewModel.kt`](android/app/src/main/java/com/example/campuslostfound/ui/home/HomeViewModel.kt) extending `BaseViewModel` and annotated with `@HiltViewModel`. It uses a coroutine scope to asynchronously fetch and expose state of campus items wrapped in `UiState`.
  - Upgraded the high-fidelity [`android/app/src/main/java/com/example/campuslostfound/ui/home/HomeScreen.kt`](android/app/src/main/java/com/example/campuslostfound/ui/home/HomeScreen.kt) dashboard to collect the reactive state flow and cleanly render standard Compose loading overlays, success cards list, or warning error screen layouts.
  - Linked the injected ViewModel inside [`android/app/src/main/java/com/example/campuslostfound/navigation/AppNavigation.kt`](android/app/src/main/java/com/example/campuslostfound/navigation/AppNavigation.kt) with `hiltViewModel()`.
- **Created Packages & Files:**
  - [`android/app/src/main/java/com/example/campuslostfound/ui/home/HomeViewModel.kt`](android/app/src/main/java/com/example/campuslostfound/ui/home/HomeViewModel.kt) (Home screen state management)

### Task 7.1 — Create Home Screen
Implemented a high-fidelity interactive dashboard screen for the home screen.
- **Created/Modified:**
  - [`android/app/src/main/java/com/example/campuslostfound/ui/home/HomeScreen.kt`](android/app/src/main/java/com/example/campuslostfound/ui/home/HomeScreen.kt)
- **Implementation:**
  - Integrated top app bars including circular user profile avatars, notification bells, search boxes, scrollable category pill rows, three-column location selectors, and interactive status buttons.
  - Implemented beautiful cards (`FoundItemCard`) matching specific status color cues (Unsettled, Pending, Claimed), custody location details, and action buttons.
  - Triggers a beautiful chooser dialog when clicking the bottom navigation 'Report' button to choose between "Report Lost Item" vs "Report Found Item".
- **Verification:**
  - Emitted via `UiState.Success` inside `HomeScreen` and fully reactive to viewmodel streams.

### Task 11.1 — Create category constants
Implemented a centralized, type-safe representation for fixed campus item categories.
- **Created/Modified:**
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt)
- **Implementation:**
  - Established the `ItemCategory` sealed interface to define structural properties (`id`, `displayNameResId`, and `iconResId`) required by all category constants.
  - Implemented a companion object registry containing a centralized `values` list and `fromId` mapping utility to prevent scattered category strings, decouple DB fields, and enable Clean Architecture reuse.
- **Verification:**
  - Standard Kotlin compilation via Gradle builds successfully without warnings or exceptions.

### Task 11.2 — Add Shoes category
Implemented the concrete `Shoes` category.
- **Created/Modified:**
  - [`android/app/src/main/res/values/strings.xml`](android/app/src/main/res/values/strings.xml)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt)
- **Implementation:**
  - Added localized string `category_shoes` with a value of "Shoes".
  - Defined `ItemCategory.Shoes` data object carrying unique ID `"shoes"`, display name resource reference, and null icon reference.
  - Registered `Shoes` within [`ItemCategory.values`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt) in the companion object.
- **Verification:**
  - Successful Gradle build via `./gradlew compileDebugKotlin` verifies correct integration and type safety.

### Task 11.3 — Add Blazer category
Implemented the concrete `Blazer` category.
- **Created/Modified:**
  - [`android/app/src/main/res/values/strings.xml`](android/app/src/main/res/values/strings.xml)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt)
- **Implementation:**
  - Added localized string `category_blazer` with a value of "Blazer".
  - Defined `ItemCategory.Blazer` data object carrying unique ID `"blazer"`, display name resource reference, and null icon reference.
  - Registered `Blazer` within [`ItemCategory.values`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt) in the companion object.
- **Verification:**
  - Successful Gradle build via `./gradlew compileDebugKotlin` verifies correct integration and type safety.

### Task 11.4 — Add Phone category
Implemented the concrete `Phone` category.
- **Created/Modified:**
  - [`android/app/src/main/res/values/strings.xml`](android/app/src/main/res/values/strings.xml)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt)
- **Implementation:**
  - Added localized string `category_phone` with a value of "Phone".
  - Defined `ItemCategory.Phone` data object carrying unique ID `"phone"`, display name resource reference, and null icon reference.
  - Registered `Phone` within [`ItemCategory.values`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt) in the companion object.
- **Verification:**
  - Successful Gradle build via `./gradlew compileDebugKotlin` verifies correct integration and type safety.

### Task 11.5 — Add Wallet category
Implemented the concrete `Wallet` category.
- **Created/Modified:**
  - [`android/app/src/main/res/values/strings.xml`](android/app/src/main/res/values/strings.xml)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt)
  - [`android/app/src/main/java/com/example/campuslostfound/ui/home/HomeScreen.kt`](android/app/src/main/java/com/example/campuslostfound/ui/home/HomeScreen.kt)
- **Implementation:**
  - Added localized string `category_wallet` with a value of "Wallet" inside strings resource.
  - Defined `ItemCategory.Wallet` data object carrying unique ID `"wallet"`, display name resource reference, and null icon reference.
  - Registered `Wallet` within [`ItemCategory.values`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt) in the companion object.
  - Added `"Wallet"` to the horizontally scrollable list of category filters, mapped it to beautiful visual colors and a lock icon in `FoundItemCard`, and added a new high-fidelity mockup wallet item `Brown Leather Bi-Fold Wallet` to the dashboard list.
- **Verification:**
  - Successful Gradle build via `./gradlew compileDebugKotlin` verifies correct integration and type safety.

### Task 11.6 — Add School ID category
Implemented the concrete `School ID` category.
- **Created/Modified:**
  - [`android/app/src/main/res/values/strings.xml`](android/app/src/main/res/values/strings.xml)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt)
- **Implementation:**
  - Added localized string `category_school_id` with a value of "School ID" inside strings resource.
  - Defined `ItemCategory.SchoolID` data object carrying unique ID `"school_id"`, display name resource reference, and null icon reference.
  - Registered `SchoolID` within [`ItemCategory.values`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt) in the companion object.
- **Verification:**
  - Successful Gradle build via `./gradlew compileDebugKotlin` verifies correct integration and type safety.

### Task 11.7 — Add Jacket category
Implemented the concrete `Jacket` category.
- **Created/Modified:**
  - [`android/app/src/main/res/values/strings.xml`](android/app/src/main/res/values/strings.xml)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt)
- **Implementation:**
  - Added localized string `category_jacket` with a value of "Jacket" inside strings resource.
  - Defined `ItemCategory.Jacket` data object carrying unique ID `"jacket"`, display name resource reference, and null icon reference.
  - Registered `Jacket` within [`ItemCategory.values`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt) in the companion object.
- **Verification:**
  - Successful Gradle build via `.\gradlew.bat compileDebugKotlin` verifies correct integration and type safety.

### Task 11.8 — Add Uniform category
Implemented the concrete `Uniform` category.
- **Created/Modified:**
  - [`android/app/src/main/res/values/strings.xml`](android/app/src/main/res/values/strings.xml)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt)
- **Implementation:**
  - Added localized string `category_uniform` with a value of "Uniform" inside strings resource.
  - Defined `ItemCategory.Uniform` data object carrying unique ID `"uniform"`, display name resource reference, and null icon reference.
  - Registered `Uniform` within [`ItemCategory.values`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt) in the companion object.
- **Verification:**
  - Successful Gradle build via `.\gradlew.bat compileDebugKotlin` verifies correct integration and type safety.

### Task 11.9 — Add Tumbler category
Implemented the concrete `Tumbler` category.
- **Created/Modified:**
  - [`android/app/src/main/res/values/strings.xml`](android/app/src/main/res/values/strings.xml)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt)
- **Implementation:**
  - Added localized string `category_tumbler` with a value of "Tumbler" inside strings resource.
  - Defined `ItemCategory.Tumbler` data object carrying unique ID `"tumbler"`, display name resource reference, and null icon reference.
  - Registered `Tumbler` within [`ItemCategory.values`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt) in the companion object.
- **Verification:**
  - Successful Gradle build via `.\gradlew.bat compileDebugKotlin` verifies correct integration and type safety.

### Task 11.10 — Add Mini Fan category
Implemented the concrete `Mini Fan` category.
- **Created/Modified:**
  - [`android/app/src/main/res/values/strings.xml`](android/app/src/main/res/values/strings.xml)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt)
- **Implementation:**
  - Added localized string `category_mini_fan` with a value of "Mini Fan" inside strings resource.
  - Defined `ItemCategory.MiniFan` data object carrying unique ID `"mini_fan"`, display name resource reference, and null icon reference.
  - Registered `MiniFan` within [`ItemCategory.values`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt) in the companion object.
- **Verification:**
  - Successful Gradle build via `.\gradlew.bat compileDebugKotlin` verifies correct integration and type safety.

### Task 11.11 — Add Umbrella category
Implemented the concrete `Umbrella` category.
- **Created/Modified:**
  - [`android/app/src/main/res/values/strings.xml`](android/app/src/main/res/values/strings.xml)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt)
- **Implementation:**
  - Added localized string `category_umbrella` with a value of "Umbrella" inside strings resource.
  - Defined `ItemCategory.Umbrella` data object carrying unique ID `"umbrella"`, display name resource reference, and null icon reference.
  - Registered `Umbrella` within [`ItemCategory.values`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt) in the companion object.
- **Verification:**
  - Successful Gradle build via `.\gradlew.bat compileDebugKotlin` verifies correct integration and type safety.

### Task 11.12 — Add Others category
Implemented the concrete `Others` category.
- **Created/Modified:**
  - [`android/app/src/main/res/values/strings.xml`](android/app/src/main/res/values/strings.xml)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt)
- **Implementation:**
  - Added localized string `category_others` with a value of "Others" inside strings resource.
  - Defined `ItemCategory.Others` data object carrying unique ID `"others"`, display name resource reference, and null icon reference.
  - Registered `Others` within [`ItemCategory.values`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt) in the companion object.
- **Verification:**
  - Successful Gradle build via `.\gradlew.bat compileDebugKotlin` verifies correct integration and type safety.

### Task 11.13 — Create category icons
Created Lucide SVG vector drawable icons for all 11 item categories and linked them in domain models.
- **Created/Modified:**
  - [`android/app/src/main/res/drawable/ic_category_shoes.xml`](android/app/src/main/res/drawable/ic_category_shoes.xml)
  - [`android/app/src/main/res/drawable/ic_category_blazer.xml`](android/app/src/main/res/drawable/ic_category_blazer.xml)
  - [`android/app/src/main/res/drawable/ic_category_phone.xml`](android/app/src/main/res/drawable/ic_category_phone.xml)
  - [`android/app/src/main/res/drawable/ic_category_wallet.xml`](android/app/src/main/res/drawable/ic_category_wallet.xml)
  - [`android/app/src/main/res/drawable/ic_category_school_id.xml`](android/app/src/main/res/drawable/ic_category_school_id.xml)
  - [`android/app/src/main/res/drawable/ic_category_jacket.xml`](android/app/src/main/res/drawable/ic_category_jacket.xml)
  - [`android/app/src/main/res/drawable/ic_category_uniform.xml`](android/app/src/main/res/drawable/ic_category_uniform.xml)
  - [`android/app/src/main/res/drawable/ic_category_tumbler.xml`](android/app/src/main/res/drawable/ic_category_tumbler.xml)
  - [`android/app/src/main/res/drawable/ic_category_mini_fan.xml`](android/app/src/main/res/drawable/ic_category_mini_fan.xml)
  - [`android/app/src/main/res/drawable/ic_category_umbrella.xml`](android/app/src/main/res/drawable/ic_category_umbrella.xml)
  - [`android/app/src/main/res/drawable/ic_category_others.xml`](android/app/src/main/res/drawable/ic_category_others.xml)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt)
- **Implementation:**
  - Generated Lucide-styled vector drawable assets (`ic_category_*.xml`) for all item categories.
  - Assigned each `R.drawable.ic_category_*` resource to the corresponding [`ItemCategory`](android/app/src/main/java/com/example/campuslostfound/domain/model/ItemCategory.kt) object's `iconResId` property.
- **Verification:**
  - Clean Kotlin compilation via `.\gradlew.bat compileDebugKotlin` verified all drawable resource references.

### Task 11.14 — Verify category selection
Implemented unit test coverage and verified UI integration for item category selection.
- **Created/Modified:**
  - [`android/app/src/test/java/com/example/campuslostfound/domain/model/ItemCategoryTest.kt`](android/app/src/test/java/com/example/campuslostfound/domain/model/ItemCategoryTest.kt)
  - [`android/app/src/main/java/com/example/campuslostfound/ui/home/HomeScreen.kt`](android/app/src/main/java/com/example/campuslostfound/ui/home/HomeScreen.kt)
- **Implementation:**
  - Created unit test suite verifying all 11 categories in `ItemCategory.values`, unique non-empty IDs, non-null resource IDs, and case-insensitive `ItemCategory.fromId` lookup behavior.
  - Updated horizontal category filter scroll list in `HomeScreen` to include all 11 category choices.
- **Verification:**
  - Executed `./gradlew testDebugUnitTest` and `./gradlew compileDebugKotlin` cleanly with 100% test pass rate.

### Task 12.1 — Create building data
Implemented a centralized, type-safe domain model and registry for campus buildings.
- **Created/Modified:**
  - [`android/app/src/main/res/values/strings.xml`](android/app/src/main/res/values/strings.xml)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/Building.kt`](android/app/src/main/java/com/example/campuslostfound/domain/model/Building.kt)
  - [`android/app/src/test/java/com/example/campuslostfound/domain/model/BuildingTest.kt`](android/app/src/test/java/com/example/campuslostfound/domain/model/BuildingTest.kt)
- **Implementation:**
  - Defined localized string resources (`building_main_academic`, `building_science_complex`, `building_central_library`, `building_student_center`, `building_engineering_hall`, `building_gymnasium`, `building_administration`, `building_cafeteria`) in [`strings.xml`](android/app/src/main/res/values/strings.xml).
  - Established [`Building`](android/app/src/main/java/com/example/campuslostfound/domain/model/Building.kt:1) sealed interface with concrete building objects (`MainAcademic`, `ScienceComplex`, `CentralLibrary`, `StudentCenter`, `EngineeringHall`, `Gymnasium`, `Administration`, `Cafeteria`) carrying unique IDs, short building codes, and string resource references.
  - Added companion object lookup methods `fromId(id: String)` and `fromCode(code: String)` for database and network mapping.
  - Created unit test suite [`BuildingTest.kt`](android/app/src/test/java/com/example/campuslostfound/domain/model/BuildingTest.kt:1) testing registry integrity, unique IDs/codes, string resource bindings, and lookup helpers.
- **Verification:**
  - Executed `.\gradlew.bat testDebugUnitTest compileDebugKotlin` with 100% unit test pass rate and zero compilation errors.

### Task 12.2 — Create floor data
Implemented a centralized, type-safe domain model and registry for campus building floor levels.
- **Created/Modified:**
  - [`android/app/src/main/res/values/strings.xml`](android/app/src/main/res/values/strings.xml)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/Floor.kt`](android/app/src/main/java/com/example/campuslostfound/domain/model/Floor.kt)
  - [`android/app/src/test/java/com/example/campuslostfound/domain/model/FloorTest.kt`](android/app/src/test/java/com/example/campuslostfound/domain/model/FloorTest.kt)
- **Implementation:**
  - Defined localized floor string resources (`floor_basement`, `floor_1`, `floor_2`, `floor_3`, `floor_4`, `floor_5`, `floor_roof_deck`) in [`strings.xml`](android/app/src/main/res/values/strings.xml).
  - Established [`Floor`](android/app/src/main/java/com/example/campuslostfound/domain/model/Floor.kt:12) sealed interface with concrete floor objects (`Basement`, `FirstFloor`, `SecondFloor`, `ThirdFloor`, `FourthFloor`, `FifthFloor`, `RoofDeck`) carrying unique IDs (`basement`, `floor_1`..`floor_5`, `roof_deck`), numeric levels (`-1`, `1`..`5`, `6`), display codes (`B1`, `1F`..`5F`, `RF`), and string resource references.
  - Added companion object lookup methods `fromId(id: String)`, `fromLevel(level: Int)`, and `fromCode(code: String)` for database, form, and filtering integration.
  - Created unit test suite [`FloorTest.kt`](android/app/src/test/java/com/example/campuslostfound/domain/model/FloorTest.kt:13) testing values registry size, unique IDs/levels/codes, resource bindings, and lookup helpers.
- **Verification:**
  - Executed `.\gradlew.bat testDebugUnitTest compileDebugKotlin` with 100% unit test pass rate and zero compilation errors.

### Task 12.3 — Create room data
Implemented a centralized domain model and preset registry for campus room and area locations.
- **Created/Modified:**
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/Room.kt`](android/app/src/main/java/com/example/campuslostfound/domain/model/Room.kt)
  - [`android/app/src/test/java/com/example/campuslostfound/domain/model/RoomTest.kt`](android/app/src/test/java/com/example/campuslostfound/domain/model/RoomTest.kt)
- **Implementation:**
  - Established [`Room`](android/app/src/main/java/com/example/campuslostfound/domain/model/Room.kt:1) data class carrying `id`, `name`, `number`, `buildingId`, and `floorId`.
  - Added formatted location method `getFormattedLocation(building, floor)`(android/app/src/main/java/com/example/campuslostfound/domain/model/Room.kt:29) returning hierarchical display titles (e.g. `"MAB - 1F - Computer Lab 1 (101)"`).
  - Added `Room.presets` list containing standard campus room instances (`MainLobby`, `StudyHall`, `ComputerLab1`, `ComputerLab2`, `LibraryReadingRoom`, `DeansOffice`, `StudentLounge`, `FoodCourt`, `GymnasiumCourt`) and `Room.fromCustomInput()` factory method.
  - Created unit test suite [`RoomTest.kt`](android/app/src/test/java/com/example/campuslostfound/domain/model/RoomTest.kt:1) testing preset completeness, unique IDs, title formatting, hierarchical location formatting, lookup functions, and custom input creation.
- **Verification:**
  - Executed `.\gradlew.bat testDebugUnitTest compileDebugKotlin` cleanly with 100% test pass rate.

### Task 12.4 — Create building selector
Implemented a reusable Material 3 building selector component.
- **Created/Modified:**
  - [`android/app/src/main/res/values/strings.xml`](android/app/src/main/res/values/strings.xml)
  - [`android/app/src/main/java/com/example/campuslostfound/ui/components/BuildingSelector.kt`](android/app/src/main/java/com/example/campuslostfound/ui/components/BuildingSelector.kt)
- **Implementation:**
  - Added location selector string resources (`select_building`, `building_label`, `select_floor`, `floor_label`, `room_label`, `room_placeholder`) in [`strings.xml`](android/app/src/main/res/values/strings.xml).
  - Created [`BuildingSelector`](android/app/src/main/java/com/example/campuslostfound/ui/components/BuildingSelector.kt:33) composable function using `ExposedDropdownMenuBox`(androidx/compose/material3/ExposedDropdownMenuBox:1) allowing users to choose from [`Building.values`](android/app/src/main/java/com/example/campuslostfound/domain/model/Building.kt:105).
  - Supports selection state callbacks, read-only menu anchor formatting, error messages, and enabled/disabled interactions.
- **Verification:**
  - Executed `.\gradlew.bat compileDebugKotlin testDebugUnitTest` cleanly without errors or deprecation warnings.

### Task 12.5 — Create floor selector
Implemented a reusable Material 3 floor level selector component.
- **Created/Modified:**
  - [`android/app/src/main/java/com/example/campuslostfound/ui/components/FloorSelector.kt`](android/app/src/main/java/com/example/campuslostfound/ui/components/FloorSelector.kt)
- **Implementation:**
  - Created [`FloorSelector`](android/app/src/main/java/com/example/campuslostfound/ui/components/FloorSelector.kt:38) composable function using `ExposedDropdownMenuBox`(androidx/compose/material3/ExposedDropdownMenuBox:1) allowing users to pick from [`Floor.values`](android/app/src/main/java/com/example/campuslostfound/domain/model/Floor.kt:108) (or filtered available floors).
  - Displays localized floor names along with short codes (e.g. `"1st Floor (1F)"`, `"Basement (B1)"`).
  - Supports selection state callbacks, error state helper text, custom labels/placeholders, and enabled/disabled interactions.
- **Verification:**
  - Executed `powershell -Command "cd android; .\gradlew.bat compileDebugKotlin testDebugUnitTest"` cleanly without errors.

### Task 12.6 — Create room selector/input
Implemented a reusable Material 3 room selector and freeform text input component.
- **Created/Modified:**
  - [`android/app/src/main/java/com/example/campuslostfound/ui/components/RoomSelector.kt`](android/app/src/main/java/com/example/campuslostfound/ui/components/RoomSelector.kt)
- **Implementation:**
  - Created [`RoomSelector`](android/app/src/main/java/com/example/campuslostfound/ui/components/RoomSelector.kt:38) composable function combining preset dropdown choices with freeform text entry using `ExposedDropdownMenuBox`(androidx/compose/material3/ExposedDropdownMenuBox:1) and `MenuAnchorType.PrimaryEditable`(androidx/compose/material3/MenuAnchorType.PrimaryEditable:1).
  - Dynamically filters [`Room.presets`](android/app/src/main/java/com/example/campuslostfound/domain/model/Room.kt:61) as the user types, allowing direct selection of preset rooms or custom room/area text entry.
  - Integrates seamlessly with [`Room.fromCustomInput()`](android/app/src/main/java/com/example/campuslostfound/domain/model/Room.kt:83) for domain model conversion.
- **Verification:**
  - Executed `powershell -Command "cd android; .\gradlew.bat compileDebugKotlin testDebugUnitTest"` with 100% test pass rate and zero compilation warnings.

### Task 12.7 — Display formatted location
Implemented centralized domain location formatting and reusable UI display components with Lucide location icon styling.
- **Created/Modified:**
  - [`android/app/src/main/res/drawable/ic_location_pin.xml`](android/app/src/main/res/drawable/ic_location_pin.xml)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/model/LocationFormatter.kt`](android/app/src/main/java/com/example/campuslostfound/domain/model/LocationFormatter.kt)
  - [`android/app/src/main/java/com/example/campuslostfound/ui/components/FormattedLocationText.kt`](android/app/src/main/java/com/example/campuslostfound/ui/components/FormattedLocationText.kt)
  - [`android/app/src/main/java/com/example/campuslostfound/ui/home/HomeScreen.kt`](android/app/src/main/java/com/example/campuslostfound/ui/home/HomeScreen.kt)
  - [`android/app/src/test/java/com/example/campuslostfound/domain/model/LocationFormatterTest.kt`](android/app/src/test/java/com/example/campuslostfound/domain/model/LocationFormatterTest.kt)
- **Implementation:**
  - Created Lucide-styled `ic_location_pin.xml` vector drawable asset.
  - Implemented [`LocationFormatter`](android/app/src/main/java/com/example/campuslostfound/domain/model/LocationFormatter.kt:7) domain utility supporting full (`formatFull`), short badge (`formatShort`), and ID-based (`formatFromIds`) location string formatting.
  - Created reusable [`FormattedLocationText`](android/app/src/main/java/com/example/campuslostfound/ui/components/FormattedLocationText.kt:38) composable component.
  - Updated [`HomeScreen.kt`](android/app/src/main/java/com/example/campuslostfound/ui/home/HomeScreen.kt:856) cards to render formatted location badges.
  - Wrote comprehensive unit tests in [`LocationFormatterTest.kt`](android/app/src/test/java/com/example/campuslostfound/domain/model/LocationFormatterTest.kt:6) covering full, short, and partial location formatting edge cases.
- **Verification:**
  - Executed `powershell -Command "cd android; .\gradlew.bat compileDebugKotlin testDebugUnitTest"` cleanly with 100% test pass rate.

### Task 12.8 — Validate location fields
Implemented domain validation logic and string resource error handling for campus location fields.
- **Created/Modified:**
  - [`android/app/src/main/res/values/strings.xml`](android/app/src/main/res/values/strings.xml)
  - [`android/app/src/main/java/com/example/campuslostfound/domain/usecase/LocationValidator.kt`](android/app/src/main/java/com/example/campuslostfound/domain/usecase/LocationValidator.kt)
  - [`android/app/src/test/java/com/example/campuslostfound/domain/usecase/LocationValidatorTest.kt`](android/app/src/test/java/com/example/campuslostfound/domain/usecase/LocationValidatorTest.kt)
- **Implementation:**
  - Defined localized validation string resources (`error_building_required`, `error_floor_required`, `error_room_required`, `error_room_too_long`) in [`strings.xml`](android/app/src/main/res/values/strings.xml).
  - Created [`LocationValidator`](android/app/src/main/java/com/example/campuslostfound/domain/usecase/LocationValidator.kt:20) domain use case and [`LocationValidationResult`](android/app/src/main/java/com/example/campuslostfound/domain/usecase/LocationValidator.kt:10) data model to validate building, floor, and room inputs.
  - Wrote unit tests in [`LocationValidatorTest.kt`](android/app/src/test/java/com/example/campuslostfound/domain/usecase/LocationValidatorTest.kt:12) testing missing building/floor, room length bounds (>50 chars), required fields, and ID lookup validation.
- **Verification:**
  - Executed `powershell -Command "cd android; .\gradlew.bat compileDebugKotlin testDebugUnitTest"` cleanly with 100% test pass rate.

---

## 3. Package Structure State
The application's package architecture is now cleanly organized as follows:

```text
com.example.campuslostfound
├── CampusLostFoundApp.kt (Application class with @HiltAndroidApp)
├── MainActivity.kt (Entry activity with @AndroidEntryPoint)
│
├── di/
│   └── AppModule.kt (DI Module providing Application context)
│
├── common/
│   ├── components/
│   ├── model/
│   ├── util/
│   ├── constants/
│   └── state/
│       └── UiState.kt
│
├── navigation/
│   └── AppNavigation.kt
│
├── data/
│   ├── model/
│   ├── repository/
│   ├── remote/
│   └── local/
│
├── domain/
│   ├── model/
│   │   ├── Building.kt
│   │   ├── Floor.kt
│   │   ├── ItemCategory.kt
│   │   ├── LocationFormatter.kt
│   │   └── Room.kt
│   ├── repository/
│   └── usecase/
│       └── LocationValidator.kt
│
├── viewmodel/
│   └── BaseViewModel.kt
│
├── presentation/
│   └── common/
│
└── ui/
    ├── auth/
    ├── components/
    │   ├── BuildingSelector.kt
    │   ├── CommonComponents.kt
    │   ├── EmptyStateComponent.kt
    │   ├── ErrorComponent.kt
    │   ├── FloorSelector.kt
    │   ├── FormattedLocationText.kt
    │   ├── LoadingComponent.kt
    │   └── RoomSelector.kt
    ├── home/
    ├── lostitems/
    ├── founditems/
    ├── itemdetails/
    ├── report/
    ├── profile/
    └── theme/
        ├── Color.kt
        ├── Theme.kt
        └── Type.kt
```

---

## 4. How to Compile & Verify Build
Since the system Java runtime environment might not be globally configured, run Gradle commands via PowerShell from the root or `android` directory:

```powershell
cd android; .\gradlew.bat compileDebugKotlin testDebugUnitTest
```

---

## 5. Next Steps
The next task to execute in [`TASKS.MD`](TASKS.MD) is:
- **Task 12.9:** Verify location filtering


All architectural package scaffoldings, ViewModel architecture foundations, reusable StateFlow UI state patterns, and Dagger Hilt Dependency Injection infrastructures have been successfully established and verified! All package-level architectural tasks (Tasks 3.1 through 3.10) are now fully complete.
