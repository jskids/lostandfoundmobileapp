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

### Task 3.3 — Create Shared/Common Package
Created the shared/common package structure under `com.example.campuslostfound.common` to hold reusable, application-wide components, models, constants, and utilities. Since empty directories are ignored by Git, `.gitkeep` placeholder files were utilized to preserve directories.
- **Created Packages & Files:**
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/common/components/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/common/components/.gitkeep) (reusable UI components)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/common/model/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/common/model/.gitkeep) (shared models)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/common/util/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/common/util/.gitkeep) (general helpers)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/common/constants/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/common/constants/.gitkeep) (app-wide constants)

### Task 3.4 — Create Data Package
Created the data-layer package structure under `com.example.campuslostfound.data` for repository implementations, models/DTOs, and data sources.
- **Created Packages & Files:**
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/.gitkeep) (DTOs/Models)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/repository/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/repository/.gitkeep) (Repository implementations)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/remote/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/remote/.gitkeep) (API/Network)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/local/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/local/.gitkeep) (Database/Storage)

### Task 3.5 — Create Domain Package
Created the domain-layer package structure under `com.example.campuslostfound.domain` to enforce clean architecture and keep core business logic decoupled from frameworks.
- **Created Packages & Files:**
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/.gitkeep) (Domain models)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/repository/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/repository/.gitkeep) (Repository interfaces)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/usecase/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/usecase/.gitkeep) (Use cases)

### Task 3.6 — Create UI/Feature Packages
Created presentation/UI feature package structures under `com.example.campuslostfound.ui` to isolate features.
- **Created Packages & Files:**
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/ui/auth/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/ui/auth/.gitkeep) (Authentication UI)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/ui/home/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/ui/home/.gitkeep) (Dashboard UI)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/ui/lostitems/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/ui/lostitems/.gitkeep) (Lost-item listing UI)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/ui/founditems/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/ui/founditems/.gitkeep) (Found-item listing UI)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/ui/itemdetails/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/ui/itemdetails/.gitkeep) (Item Details UI)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/ui/report/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/ui/report/.gitkeep) (Report submission UI)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/ui/profile/.gitkeep`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/ui/profile/.gitkeep) (User Profile UI)

### Task 3.7 — Configure ViewModel Architecture
Configured the foundation for the application's ViewModel layer under `com.example.campuslostfound.viewmodel`.
- **Verified & Configured Dependencies:** Added standard Jetpack Compose and AndroidX lifecycle/ViewModel dependencies `lifecycle-viewmodel-ktx` and `lifecycle-viewmodel-compose` inside [`lostandfoundmobileapp/android/gradle/libs.versions.toml`](lostandfoundmobileapp/android/gradle/libs.versions.toml) and [`lostandfoundmobileapp/android/app/build.gradle.kts`](lostandfoundmobileapp/android/app/build.gradle.kts).
- **Created Packages & Files:**
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/viewmodel/BaseViewModel.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/viewmodel/BaseViewModel.kt) (Architectural base ViewModel establishing standard rules and structure for future UI states, lifecycle-awareness, and repository communication)

### Task 3.8 — Configure StateFlow UI state pattern
Configured a clean and reusable StateFlow-based UI state pattern for future ViewModels.
- **Created Packages & Files:**
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/common/state/UiState.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/common/state/UiState.kt) (Architectural reusable sealed interface representing Loading, Success, and Error UI states)

---

## 3. Package Structure State
The application's package architecture is now cleanly organized as follows:

```text
com.example.campuslostfound
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
```,old_string:
│
├── data/
│   ├── model/
│   ├── repository/
│   ├── remote/
│   └── local/
│
├── domain/
│   ├── model/
│   ├── repository/
│   └── usecase/
│
├── viewmodel/
│   └── BaseViewModel.kt
│
└── ui/
    ├── auth/
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
Since the system Java runtime environment might not be globally configured, use Android Studio's embedded Java runtime (`JBR`) home directory to run Gradle commands:

- **Mac JBR Path:** `/Applications/Android Studio.app/Contents/jbr/Contents/Home`
- **Gradle Command:**
  ```bash
  export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home"
  cd lostandfoundmobileapp/android
  ./gradlew compileDebugSources
  ```

---

## 5. Next Steps
The next task to execute in [`lostandfoundmobileapp/TASKS.MD`](lostandfoundmobileapp/TASKS.MD) is:
- **Task 3.9:** Configure dependency injection

All architectural package scaffoldings, ViewModel architecture foundations, and reusable StateFlow UI state patterns have been successfully established! No further steps are needed for Tasks 3.3 through 3.8.
