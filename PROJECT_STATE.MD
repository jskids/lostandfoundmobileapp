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

### Task 1.9 — Configure Supabase Project
Prepared and configured the Supabase backend project environment definitions, database parameters, auth providers, storage specifications, and setup guidelines for Campus Lost & Found.
- **Created Configuration & Setup Files:**
  - [`lostandfoundmobileapp/supabase/README.md`](lostandfoundmobileapp/supabase/README.md) (Complete guide for Supabase project creation, Auth configuration, PostgreSQL setup, Storage buckets, Realtime, and safe credential management)
  - [`lostandfoundmobileapp/supabase/.env.example`](lostandfoundmobileapp/supabase/.env.example) (Template for required `SUPABASE_URL` and `SUPABASE_ANON_KEY` credentials)
  - [`lostandfoundmobileapp/supabase/config.json`](lostandfoundmobileapp/supabase/config.json) (Base configuration definition file for database, auth, storage, and realtime)
- **Security Enhancements:** Updated [`lostandfoundmobileapp/.gitignore`](lostandfoundmobileapp/.gitignore) to exclude `.env` files and secret keys.
- **Boundary Verification:** Kept the Android application completely unconnected to Supabase as specified (no Android code or SDK initialization added in Task 1.9).

### Task 1.10 — Connect Android App to Supabase
Connected the native Android app to Supabase safely and verified the integration build.
- **Gradle Dependencies:** Added Supabase Kotlin SDK 3.0.3 modules (`postgrest-kt`, `auth-kt`, `storage-kt`, `realtime-kt`) and Ktor Android engine (`ktor-client-android`) inside [`lostandfoundmobileapp/android/gradle/libs.versions.toml`](lostandfoundmobileapp/android/gradle/libs.versions.toml:1) and [`lostandfoundmobileapp/android/app/build.gradle.kts`](lostandfoundmobileapp/android/app/build.gradle.kts:1).
- **Secure Credentials Injection:** Configured `SUPABASE_URL` and `SUPABASE_ANON_KEY` in [`lostandfoundmobileapp/android/local.properties`](lostandfoundmobileapp/android/local.properties:1) and exposed them via Gradle `BuildConfig` fields to avoid exposing secrets in source code.
- **Supabase Client Provider:** Created singleton [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/remote/SupabaseClient.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/remote/SupabaseClient.kt:15) (`SupabaseClientProvider`) initializing `Auth`, `Postgrest`, `Storage`, and `Realtime`.
- **Build Verification:** Cleanly compiled Kotlin debug sources (`compileDebugKotlin`) with 0 errors.

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

### Task 4.1 — Define users/profile model
Designed and implemented the core user profile model (`UserProfile` domain model and `UserProfileDto` data model with mappers) tailored for Supabase PostgreSQL and Clean Architecture.
- **Gradle & Build Configuration:** Enabled Kotlin serialization compiler plugin (`kotlin.plugin.serialization`) in [`lostandfoundmobileapp/android/gradle/libs.versions.toml`](lostandfoundmobileapp/android/gradle/libs.versions.toml), [`lostandfoundmobileapp/android/build.gradle.kts`](lostandfoundmobileapp/android/build.gradle.kts), and [`lostandfoundmobileapp/android/app/build.gradle.kts`](lostandfoundmobileapp/android/app/build.gradle.kts).
- **Created Packages & Files:**
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/UserProfile.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/UserProfile.kt) (Domain model `UserProfile` and `UserRole` enum representing user identity, role, contact info, and preferences)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/UserProfileDto.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/UserProfileDto.kt) (Data DTO `UserProfileDto` annotated with `@Serializable` and `@SerialName` for Supabase Postgrest, along with bidirection mappers `toDomain()` and `toDto()`)
- **Build Verification:** Verified build compilation with `./gradlew compileDebugKotlin` returning 0 errors.

### Task 4.2 — Define lost/found item model
Designed and implemented the lost/found item model (`LostFoundItem` domain model and `LostFoundItemDto` data model with mappers) tailored for Clean Architecture and Supabase PostgreSQL.
- **Created Packages & Files:**
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/LostFoundItem.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/LostFoundItem.kt) (Domain model `LostFoundItem` along with `ItemType` and `ItemStatus` enums representing the item report details, location placeholders, turnover details, and status enums)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/LostFoundItemDto.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/LostFoundItemDto.kt) (Data DTO `LostFoundItemDto` annotated with `@Serializable` and `@SerialName` mapping directly to Supabase `items` table columns, alongside `toDomain()` and `toDto()` conversion functions)
- **Build Verification:** Verified build compilation with `./gradlew compileDebugKotlin` returning 0 errors.

### Task 4.3 — Define item category model
Designed and implemented the item category model (`Category` domain model and `CategoryDto` data model with mappers) tailored for Clean Architecture, aligned with UserProfile and LostFoundItem, and mapping directly to a PostgreSQL table in Supabase.
- **Created Packages & Files:**
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/Category.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/Category.kt) (Domain model `Category` and standard categories companion object containing Shoes, Blazer, Phone, Wallet, School ID, Jacket, Uniform, Tumbler, Mini Fan, Umbrella, Others)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/CategoryDto.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/CategoryDto.kt) (Data DTO `CategoryDto` annotated with `@Serializable` and `@SerialName` for Supabase `categories` table columns, alongside bidirection mappers `toDomain()` and `toDto()`)
- **Build Verification:** Verified build compilation using Android Studio's embedded JBR with `gradlew.bat compileDebugKotlin` returning 0 errors.

### Task 4.4 — Define campus location model
Designed and implemented the campus location model (`CampusLocation` domain model and `CampusLocationDto` data model with mappers) tailored for Clean Architecture and mapping directly to the `locations` PostgreSQL database table in Supabase.
- **Created Packages & Files:**
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/CampusLocation.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/CampusLocation.kt) (Domain model `CampusLocation` with `getFormattedLocation()` helper and `DEFAULT_BUILDINGS` standard campus buildings)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/CampusLocationDto.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/CampusLocationDto.kt) (Data DTO `CampusLocationDto` annotated with `@Serializable` and `@SerialName` mapping to Supabase `locations` table, alongside bidirection mappers `toDomain()` and `toDto()`)

### Task 4.5 — Define report/claim model
Designed and implemented the report/claim model (`ItemClaim` domain model with `ClaimStatus` enum and `ItemClaimDto` data model with mappers) tailored for Clean Architecture and mapping directly to the `claims` PostgreSQL database table in Supabase.
- **Created Packages & Files:**
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/ItemClaim.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/ItemClaim.kt) (Domain model `ItemClaim` and `ClaimStatus` enum representing item claim requests, proof verification fields, and administrative review status)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/ItemClaimDto.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/ItemClaimDto.kt) (Data DTO `ItemClaimDto` annotated with `@Serializable` and `@SerialName` mapping to Supabase `claims` table, alongside bidirection mappers `toDomain()` and `toDto()`)

### Task 4.6 — Define notification model
Designed and implemented the notification model (`AppNotification` domain model with `NotificationType` enum and `AppNotificationDto` data model with mappers) tailored for Clean Architecture and mapping directly to the `notifications` PostgreSQL database table in Supabase.
- **Created Packages & Files:**
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/AppNotification.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/AppNotification.kt) (Domain model `AppNotification` and `NotificationType` enum representing in-app notifications, classification, and read status)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/AppNotificationDto.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/AppNotificationDto.kt) (Data DTO `AppNotificationDto` annotated with `@Serializable` and `@SerialName` mapping to Supabase `notifications` table, alongside bidirection mappers `toDomain()` and `toDto()`)

### Task 4.7 — Define item status model
Designed and implemented the standalone item status model (`ItemStatus` rich enum in its own domain file) tailored for Clean Architecture, enforcing lifecycle states (`UNSETTLED`, `PENDING`, `CLAIMED`), display labels, descriptions, and administrative permission rules.
- **Created Packages & Files:**
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/ItemStatus.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/ItemStatus.kt) (Rich enum `ItemStatus` with UI labels, workflow descriptions, and `isAdminOnly` permission flags)
  - Refactored [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/LostFoundItem.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/LostFoundItem.kt) to utilize the standalone `ItemStatus` domain enum cleanly.

### Task 4.8 — Define submitted-to-DO field
Documented, verified, and integrated the `submittedToDO` boolean field in both the domain layer and the data layer with `@SerialName("submitted_to_do")` and custom mapping logic. This field tracks whether an item has been physically turned over to the Disciplinary Office (DO).
- **Inspected & Documented Files:**
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/LostFoundItem.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/LostFoundItem.kt) (Domain model `LostFoundItem` containing `submittedToDO` and precise KDoc documentation for tracking Disciplinary Office (DO) turnover status)
  - [`lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/LostFoundItemDto.kt`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/LostFoundItemDto.kt) (Data DTO `LostFoundItemDto` defining `@SerialName("submitted_to_do")` along with bidirection mappers `toDomain()` and `toDto()`)

### Task 4.9 — Create database tables
Designed and created the complete PostgreSQL table schema definitions/scripts in `lostandfoundmobileapp/supabase/schema.sql` for all 6 database tables required by the application DTOs. Column names, data types, nullability, and default values are strictly aligned with the `@SerialName` annotations defined in the DTOs:
- **`profiles` table** matching [`UserProfileDto`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/UserProfileDto.kt)
- **`categories` table** matching [`CategoryDto`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/CategoryDto.kt)
- **`locations` table** matching [`CampusLocationDto`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/CampusLocationDto.kt)
- **`items` table** matching [`LostFoundItemDto`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/LostFoundItemDto.kt)
- **`claims` table** matching [`ItemClaimDto`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/ItemClaimDto.kt)
- **`notifications` table** matching [`AppNotificationDto`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/data/model/AppNotificationDto.kt)
- **Created Schema File:** [`lostandfoundmobileapp/supabase/schema.sql`](lostandfoundmobileapp/supabase/schema.sql) containing PostgreSQL table creation statements, default values, and data types (UUID, TEXT, BOOLEAN, and TIMESTAMP WITH TIME ZONE).

### Task 4.10 — Create database relationships
Updated and configured database relationships, foreign key constraints, and cascading rules across all entities inside [`lostandfoundmobileapp/supabase/schema.sql`](lostandfoundmobileapp/supabase/schema.sql:1):
- **`profiles.id`**: Foreign key referencing `auth.users(id)` with `ON DELETE CASCADE`.
- **`items.user_id`**: Foreign key referencing `profiles.id` with `ON DELETE CASCADE`.
- **`items.category_id`**: Foreign key referencing `categories.id` with `ON DELETE SET NULL`.
- **`items.location_id`**: Foreign key referencing `locations.id` with `ON DELETE SET NULL`.
- **`claims.item_id`**: Foreign key referencing `items.id` with `ON DELETE CASCADE`.
- **`claims.claimer_id`**: Foreign key referencing `profiles.id` with `ON DELETE CASCADE`.
- **`notifications.user_id`**: Foreign key referencing `profiles.id` with `ON DELETE CASCADE`.
- **`notifications.related_item_id`**: Foreign key referencing `items.id` with `ON DELETE CASCADE`.
- **`notifications.related_claim_id`**: Foreign key referencing `claims.id` with `ON DELETE CASCADE`.

### Task 4.11 — Create database indexes
Designed and added database indexes inside [`lostandfoundmobileapp/supabase/schema.sql`](lostandfoundmobileapp/supabase/schema.sql:122) to optimize foreign key lookups, frequent status and category filtering, user-based querying, unread notification queries, and timestamp sorting:
- **`profiles` indexes**: `idx_profiles_email` on `email`, `idx_profiles_role` on `role`.
- **`items` indexes**: `idx_items_user_id` on `user_id`, `idx_items_category_id` on `category_id`, `idx_items_location_id` on `location_id`, `idx_items_type` on `type`, `idx_items_status` on `status`, `idx_items_submitted_to_do` on `submitted_to_do`, `idx_items_created_at` on `created_at DESC`, and `idx_items_type_status` on `(type, status)`.
- **`claims` indexes**: `idx_claims_item_id` on `item_id`, `idx_claims_claimer_id` on `claimer_id`, `idx_claims_status` on `status`, and `idx_claims_created_at` on `created_at DESC`.
- **`notifications` indexes**: `idx_notifications_user_id` on `user_id`, `idx_notifications_related_item_id` on `related_item_id`, `idx_notifications_related_claim_id` on `related_claim_id`, `idx_notifications_is_read` on `is_read`, `idx_notifications_user_is_read` on `(user_id, is_read)`, and `idx_notifications_created_at` on `created_at DESC`.

### Task 4.12 — Create database constraints
Designed and implemented data integrity constraints, CHECK constraints, and UNIQUE constraints inside [`lostandfoundmobileapp/supabase/schema.sql`](lostandfoundmobileapp/supabase/schema.sql:164) and directly within table DDL statements:
- **`profiles` constraints**:
  - `chk_profiles_role`: `CHECK (role IN ('student', 'admin', 'staff'))` matching [`UserRole`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/UserProfile.kt:6).
  - `unique_profiles_email`: `UNIQUE (email)` to ensure one profile per email address.
  - `unique_profiles_student_id`: `UNIQUE (student_id)` to prevent duplicate campus student ID registrations.
- **`categories` constraints**:
  - `unique_categories_name`: `UNIQUE (name)` to prevent duplicate category definitions.
- **`items` constraints**:
  - `chk_items_type`: `CHECK (type IN ('LOST', 'FOUND'))` matching [`ItemType`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/LostFoundItem.kt:6).
  - `chk_items_status`: `CHECK (status IN ('UNSETTLED', 'PENDING', 'CLAIMED'))` matching [`ItemStatus`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/ItemStatus.kt:12).
- **`claims` constraints**:
  - `chk_claims_status`: `CHECK (status IN ('PENDING', 'APPROVED', 'REJECTED', 'CANCELLED'))` matching [`ClaimStatus`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/ItemClaim.kt:6).
  - `unique_claims_item_claimer`: `UNIQUE (item_id, claimer_id)` to prevent duplicate claims by the same user on an item.
- **`notifications` constraints**:
  - `chk_notifications_type`: `CHECK (type IN ('NEW_ITEM', 'CLAIM_UPDATE', 'MENTION', 'SYSTEM'))` matching [`NotificationType`](lostandfoundmobileapp/android/app/src/main/java/com/example/campuslostfound/domain/model/AppNotification.kt:6).
- **Safe Execution DDL**: Configured an idempotent `DO $$ BEGIN ... END $$;` block checking `pg_constraint` before executing `ALTER TABLE ... ADD CONSTRAINT` for pre-existing deployments.

### Task 4.13 — Create database migration scripts
Created modular, versioned migration scripts representing the complete setup DDL from `schema.sql`.
- **Created Migration Directory & Script**: Created [`lostandfoundmobileapp/supabase/migrations/20260917000000_initial_schema.sql`](lostandfoundmobileapp/supabase/migrations/20260917000000_initial_schema.sql) containing the complete database setup schema, foreign key relations, custom indexes, and CHECK/UNIQUE constraints.
- **Ensured Idempotency**: Migration scripts are designed using `CREATE TABLE IF NOT EXISTS`, `CREATE INDEX IF NOT EXISTS`, and safe `ALTER TABLE ... ADD CONSTRAINT` wrapped in `DO` blocks to ensure compatibility with both greenfield setups and existing schema upgrades.

### Task 4.14 — Verify database schema
Verified the accuracy, constraints, relationships, and Kotlin DTO compatibility for the complete PostgreSQL database schema definitions.
- **DTO vs Schema Compatibility Matrix:**
  - `UserProfileDto` ↔ `profiles` table: 100% matched (`id`, `full_name`, `email`, `student_id`, `phone_number`, `avatar_url`, `role`, `is_anonymous_default`, `created_at`, `updated_at`).
  - `LostFoundItemDto` ↔ `items` table: 100% matched (`id`, `user_id`, `title`, `description`, `type`, `category_id`, `location_id`, `building`, `floor`, `room`, `date_reported`, `image_url`, `is_anonymous`, `submitted_to_do`, `status`, `created_at`, `updated_at`).
  - `CategoryDto` ↔ `categories` table: 100% matched (`id`, `name`, `icon_name`, `description`, `created_at`, `updated_at`).
  - `CampusLocationDto` ↔ `locations` table: 100% matched (`id`, `building`, `floor`, `room`, `description`, `created_at`, `updated_at`).
  - `ItemClaimDto` ↔ `claims` table: 100% matched (`id`, `item_id`, `claimer_id`, `proof_description`, `proof_image_url`, `status`, `admin_notes`, `created_at`, `updated_at`).
  - `AppNotificationDto` ↔ `notifications` table: 100% matched (`id`, `user_id`, `title`, `message`, `type`, `related_item_id`, `related_claim_id`, `is_read`, `created_at`).
- **Build Verification:** Successfully executed `compileDebugKotlin` with 0 errors.
- **User Action Required:** <span style="color:red; font-weight:bold;">USER ACTION REQUIRED: Apply the migration script lostandfoundmobileapp/supabase/migrations/20260917000000_initial_schema.sql (or lostandfoundmobileapp/supabase/schema.sql) in your Supabase project SQL Editor or via Supabase CLI (`supabase db push` / `supabase migration up`).</span>

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
│
├── data/
│   ├── model/
│   │   ├── AppNotificationDto.kt
│   │   ├── CampusLocationDto.kt
│   │   ├── CategoryDto.kt
│   │   ├── ItemClaimDto.kt
│   │   ├── LostFoundItemDto.kt
│   │   └── UserProfileDto.kt
│   ├── repository/
│   ├── remote/
│   └── local/
│
├── domain/
│   ├── model/
│   │   ├── AppNotification.kt
│   │   ├── CampusLocation.kt
│   │   ├── Category.kt
│   │   ├── ItemClaim.kt
│   │   ├── ItemStatus.kt
│   │   ├── LostFoundItem.kt
│   │   └── UserProfile.kt
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

- **Windows Command:**
  ```cmd
  set "JAVA_HOME=C:\Program Files\Android\Android Studio\jbr"
  cd lostandfoundmobileapp\android
  gradlew.bat compileDebugKotlin
  ```

---

## 5. Next Steps
The next task to execute in [`lostandfoundmobileapp/TASKS.MD`](lostandfoundmobileapp/TASKS.MD:112) is:
- **Task 5.1:** Configure Row Level Security

Task 4.14 (Verify database schema) is fully completed and verified!
