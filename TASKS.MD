# TASKS — Execution Plan

> **Purpose:** The singular source of truth for execution sequencing and progress tracking. AI agents must execute exactly one numbered task at a time as directed by the user.
>
> **Project:** Campus Lost & Found
>
> **Platform:** Native Android
>
> **Frontend:** Kotlin + Jetpack Compose + Material 3
>
> **Backend:** Supabase
>
> **Database:** PostgreSQL via Supabase
>
> **Authentication:** Supabase Auth
>
> **Storage:** Supabase Storage
>
> **Realtime:** Supabase Realtime
>
> **Architecture:** MVVM
>
> **Rule:** Execute exactly ONE numbered task at a time. Do not implement future tasks unless explicitly instructed.

*Last updated: [DATE]*

---

How to use this:

Point the AI at one numbered task per session.

> "Do 1.1"

Not:

> "Build the entire app."

A task is too big if you can't describe it in one line.

**Status:** `[ ]` todo · `[~]` in progress · `[x]` done · `[!]` blocked

---

# 1. Project Setup

* [x] 1.1 Create native Android project scaffold
* [X] 1.2 Configure Kotlin and Jetpack Compose
* [X] 1.3 Configure Material 3
* [X] 1.4 Create base package/folder structure
* [X] 1.5 Configure Gradle dependencies
* [X] 1.6 Configure minimum/target Android SDK
* [X] 1.7 Configure Git repository and `.gitignore`
* [X] 1.8 Verify clean project build
* [ ] 1.9 Configure Supabase project
* [ ] 1.10 Connect Android app to Supabase

---

# 2. Design System

* [X] 2.1 Define app color palette
* [X] 2.2 Define typography
* [X] 2.3 Configure application font
* [X] 2.4 Configure Material 3 theme
* [X] 2.5 Define spacing and sizing constants
* [X] 2.6 Define reusable shape/radius tokens
* [X] 2.7 Define reusable UI components
* [X] 2.8 Create app icon
* [X] 2.9 Create reusable loading component
* [X] 2.10 Create reusable error component
* [X] 2.11 Create reusable empty-state component

---

# 3. Application Architecture

* [ ] 3.1 Create MVVM package structure
* [X] 3.2 Create navigation package
* [X] 3.3 Create shared/common package
* [X] 3.4 Create data package
* [X] 3.5 Create domain package
* [X] 3.6 Create UI/feature packages
* [x] 3.7 Configure ViewModel architecture
* [x] 3.8 Configure StateFlow UI state pattern
* [ ] 3.9 Configure dependency injection
* [ ] 3.10 Verify architecture with a sample screen

---

# 4. Database Design

* [ ] 4.1 Define users/profile model
* [ ] 4.2 Define lost/found item model
* [ ] 4.3 Define item category model
* [ ] 4.4 Define campus location model
* [ ] 4.5 Define report/claim model
* [ ] 4.6 Define notification model
* [ ] 4.7 Define item status model
* [ ] 4.8 Define submitted-to-DO field
* [ ] 4.9 Create database tables
* [ ] 4.10 Create database relationships
* [ ] 4.11 Create database indexes
* [ ] 4.12 Create database constraints
* [ ] 4.13 Create database migration scripts
* [ ] 4.14 Verify database schema

---

# 5. Supabase Security

* [ ] 5.1 Configure Row Level Security
* [ ] 5.2 Create authenticated user policies
* [ ] 5.3 Create public item viewing policy
* [ ] 5.4 Create item creation policy
* [ ] 5.5 Create item update policy
* [ ] 5.6 Create item deletion policy
* [ ] 5.7 Restrict status management to admins
* [ ] 5.8 Restrict submitted-to-DO management to admins
* [ ] 5.9 Configure profile access policies
* [ ] 5.10 Configure notification access policies
* [ ] 5.11 Test RLS policies

---

# 6. Authentication

* [ ] 6.1 Configure Supabase email authentication
* [ ] 6.2 Create authentication repository
* [ ] 6.3 Create authentication state
* [ ] 6.4 Create login screen
* [ ] 6.5 Create registration screen
* [ ] 6.6 Connect login to Supabase Auth
* [ ] 6.7 Connect registration to Supabase Auth
* [ ] 6.8 Create session persistence
* [ ] 6.9 Create logout functionality
* [ ] 6.10 Create authentication loading state
* [ ] 6.11 Create authentication error handling
* [ ] 6.12 Create protected navigation
* [ ] 6.13 Create account/profile initialization
* [ ] 6.14 Verify authentication flow

---

# 7. Home / Discover

* [x] 7.1 Create home screen
* [ ] 7.2 Create recent items section
* [ ] 7.3 Create lost items section
* [ ] 7.4 Create found items section
* [ ] 7.5 Create category shortcuts
* [ ] 7.6 Create search entry point
* [ ] 7.7 Create item card component
* [ ] 7.8 Display item image
* [ ] 7.9 Display item category
* [ ] 7.10 Display item location
* [ ] 7.11 Display item status
* [ ] 7.12 Display posted date
* [ ] 7.13 Connect home screen to Supabase
* [ ] 7.14 Add home loading state
* [ ] 7.15 Add home error state
* [ ] 7.16 Add home empty state

---

# 8. Lost & Found Items

* [ ] 8.1 Create lost/found item model
* [ ] 8.2 Create item repository
* [ ] 8.3 Create item list query
* [ ] 8.4 Create item detail query
* [ ] 8.5 Create item card
* [ ] 8.6 Create item detail screen
* [ ] 8.7 Display item category
* [ ] 8.8 Display item image
* [ ] 8.9 Display description
* [ ] 8.10 Display date and time posted
* [ ] 8.11 Display campus location
* [ ] 8.12 Display account/anonymous information
* [ ] 8.13 Display submitted-to-DO status
* [ ] 8.14 Display current item status
* [ ] 8.15 Add item refresh
* [ ] 8.16 Add item loading state
* [ ] 8.17 Add item error state
* [ ] 8.18 Add item empty state

---

# 9. Report — I Lost Something

* [ ] 9.1 Create "I Lost Something" entry point
* [ ] 9.2 Create lost-item form
* [ ] 9.3 Create category selector
* [ ] 9.4 Create building selector
* [ ] 9.5 Create floor selector
* [ ] 9.6 Create room input
* [ ] 9.7 Create date lost input
* [ ] 9.8 Create description input
* [ ] 9.9 Create image picker
* [ ] 9.10 Create image preview
* [ ] 9.11 Create anonymous/account selector
* [ ] 9.12 Validate lost-item form
* [ ] 9.13 Upload lost-item image
* [ ] 9.14 Submit lost-item report
* [ ] 9.15 Create success state
* [ ] 9.16 Create submission error state
* [ ] 9.17 Verify lost-item creation

---

# 10. Report — I Found Something

* [ ] 10.1 Create "I Found Something" entry point
* [ ] 10.2 Create found-item form
* [ ] 10.3 Create category selector
* [ ] 10.4 Create building selector
* [ ] 10.5 Create floor selector
* [ ] 10.6 Create room input
* [ ] 10.7 Create date found input
* [ ] 10.8 Create description input
* [ ] 10.9 Create image picker
* [ ] 10.10 Create image preview
* [ ] 10.11 Create anonymous/account selector
* [ ] 10.12 Validate found-item form
* [ ] 10.13 Upload found-item image
* [ ] 10.14 Submit found-item report
* [ ] 10.15 Create success state
* [ ] 10.16 Create submission error state
* [ ] 10.17 Verify found-item creation

---

# 11. Item Categories

* [ ] 11.1 Create category constants
* [ ] 11.2 Add Shoes category
* [ ] 11.3 Add Blazer category
* [ ] 11.4 Add Phone category
* [ ] 11.5 Add Wallet category
* [ ] 11.6 Add School ID category
* [ ] 11.7 Add Jacket category
* [ ] 11.8 Add Uniform category
* [ ] 11.9 Add Tumbler category
* [ ] 11.10 Add Mini Fan category
* [ ] 11.11 Add Umbrella category
* [ ] 11.12 Add Others category
* [ ] 11.13 Create category icons
* [ ] 11.14 Verify category selection

---

# 12. Campus Location

* [ ] 12.1 Create building data
* [ ] 12.2 Create floor data
* [ ] 12.3 Create room data
* [ ] 12.4 Create building selector
* [ ] 12.5 Create floor selector
* [ ] 12.6 Create room selector/input
* [ ] 12.7 Display formatted location
* [ ] 12.8 Validate location fields
* [ ] 12.9 Verify location filtering

---

# 13. Search & Filtering

* [ ] 13.1 Create search screen
* [ ] 13.2 Create search input
* [ ] 13.3 Search item title/description
* [ ] 13.4 Add category filter
* [ ] 13.5 Add lost/found filter
* [ ] 13.6 Add status filter
* [ ] 13.7 Add building filter
* [ ] 13.8 Add floor filter
* [ ] 13.9 Add date filter
* [ ] 13.10 Create filter UI
* [ ] 13.11 Create clear filters action
* [ ] 13.12 Create no-results state
* [ ] 13.13 Optimize search query
* [ ] 13.14 Verify search and filters

---

# 14. Item Claims

* [ ] 14.1 Create claim model
* [ ] 14.2 Create claim repository
* [ ] 14.3 Create claim action
* [ ] 14.4 Create claim form
* [ ] 14.5 Create claim description/proof field
* [ ] 14.6 Create claim submission
* [ ] 14.7 Create claim pending state
* [ ] 14.8 Display claim status
* [ ] 14.9 Prevent duplicate claims
* [ ] 14.10 Restrict claim management to admins
* [ ] 14.11 Verify claim flow

---

# 15. Admin

* [ ] 15.1 Create admin role
* [ ] 15.2 Create admin navigation
* [ ] 15.3 Create admin dashboard
* [ ] 15.4 Create all-items management screen
* [ ] 15.5 Create pending claims screen
* [ ] 15.6 Create item management screen
* [ ] 15.7 Create item status controls
* [ ] 15.8 Implement Unsettled status
* [ ] 15.9 Implement Pending status
* [ ] 15.10 Implement Claimed status
* [ ] 15.11 Restrict status changes to admin
* [ ] 15.12 Create submitted-to-DO control
* [ ] 15.13 Restrict submitted-to-DO changes to admin
* [ ] 15.14 Create item moderation
* [ ] 15.15 Create item deletion
* [ ] 15.16 Create admin confirmation dialogs
* [ ] 15.17 Verify admin permissions

---

# 16. Submitted to DO

* [ ] 16.1 Add submitted-to-DO field
* [ ] 16.2 Display DO submission status
* [ ] 16.3 Create admin DO status control
* [ ] 16.4 Add submitted-to-DO filter
* [ ] 16.5 Add DO status to item details
* [ ] 16.6 Verify DO workflow

---

# 17. Notifications

* [ ] 17.1 Create notification model
* [ ] 17.2 Create notification repository
* [ ] 17.3 Create notification screen
* [ ] 17.4 Create new-item notification
* [ ] 17.5 Create mention notification
* [ ] 17.6 Display unread notification count
* [ ] 17.7 Create read/unread state
* [ ] 17.8 Mark notification as read
* [ ] 17.9 Create notification navigation
* [ ] 17.10 Configure Supabase Realtime
* [ ] 17.11 Connect realtime notification updates
* [ ] 17.12 Verify notification flow

---

# 18. Mentions

* [ ] 18.1 Define mention behavior
* [ ] 18.2 Create mention data model
* [ ] 18.3 Create mention detection
* [ ] 18.4 Create mention notification
* [ ] 18.5 Display mentioned user
* [ ] 18.6 Navigate from mention notification
* [ ] 18.7 Verify mention flow

---

# 19. My Reports

* [ ] 19.1 Create My Reports screen
* [ ] 19.2 Display user's lost reports
* [ ] 19.3 Display user's found reports
* [ ] 19.4 Filter reports by status
* [ ] 19.5 Display Unsettled reports
* [ ] 19.6 Display Pending reports
* [ ] 19.7 Display Claimed reports
* [ ] 19.8 Create report detail screen
* [ ] 19.9 Add report refresh
* [ ] 19.10 Verify My Reports

---

# 20. Account & Profile

* [ ] 20.1 Create account screen
* [ ] 20.2 Display student email
* [ ] 20.3 Display account information
* [ ] 20.4 Display user's reports
* [ ] 20.5 Create anonymous posting preference
* [ ] 20.6 Create notification preferences
* [ ] 20.7 Create logout action
* [ ] 20.8 Verify account flow

---

# 21. Image & Storage

* [ ] 21.1 Create Supabase Storage bucket
* [ ] 21.2 Configure storage policies
* [ ] 21.3 Create image upload repository
* [ ] 21.4 Create image picker
* [ ] 21.5 Add image compression
* [ ] 21.6 Add image upload progress
* [ ] 21.7 Create image preview
* [ ] 21.8 Create image deletion
* [ ] 21.9 Handle upload errors
* [ ] 21.10 Verify image workflow

---

# 22. Navigation

* [ ] 22.1 Create root navigation
* [ ] 22.2 Create authentication navigation
* [ ] 22.3 Create bottom navigation
* [ ] 22.4 Add Home destination
* [ ] 22.5 Add Search destination
* [ ] 22.6 Add Report destination
* [ ] 22.7 Add Notifications destination
* [ ] 22.8 Add Account destination
* [ ] 22.9 Add Item Details destination
* [ ] 22.10 Add My Reports destination
* [ ] 22.11 Add Admin destinations
* [ ] 22.12 Protect admin routes
* [ ] 22.13 Verify navigation flow

---

# 23. Loading, Error & Empty States

* [ ] 23.1 Audit loading states
* [ ] 23.2 Audit error states
* [ ] 23.3 Audit empty states
* [ ] 23.4 Add retry actions
* [ ] 23.5 Add offline/error messaging
* [ ] 23.6 Add form validation messages
* [ ] 23.7 Add upload failure handling
* [ ] 23.8 Add authentication failure handling

---

# 24. Accessibility & UX

* [ ] 24.1 Audit touch target sizes
* [ ] 24.2 Add content descriptions
* [ ] 24.3 Audit text readability
* [ ] 24.4 Audit color contrast
* [ ] 24.5 Audit keyboard behavior
* [ ] 24.6 Audit screen reader behavior
* [ ] 24.7 Audit loading feedback
* [ ] 24.8 Audit error feedback
* [ ] 24.9 Audit confirmation dialogs
* [ ] 24.10 Audit navigation usability

---

# 25. Testing

* [ ] 25.1 Create unit test structure
* [ ] 25.2 Test authentication
* [ ] 25.3 Test item repository
* [ ] 25.4 Test item creation
* [ ] 25.5 Test item retrieval
* [ ] 25.6 Test search
* [ ] 25.7 Test filtering
* [ ] 25.8 Test claims
* [ ] 25.9 Test notifications
* [ ] 25.10 Test admin permissions
* [ ] 25.11 Test image uploads
* [ ] 25.12 Test navigation
* [ ] 25.13 Test form validation
* [ ] 25.14 Test critical user flows

---

# 26. Security Audit

* [ ] 26.1 Audit authentication security
* [ ] 26.2 Audit Supabase RLS policies
* [ ] 26.3 Audit admin permissions
* [ ] 26.4 Audit anonymous posting
* [ ] 26.5 Audit image storage permissions
* [ ] 26.6 Audit user data exposure
* [ ] 26.7 Audit claim data exposure
* [ ] 26.8 Audit notification access
* [ ] 26.9 Audit sensitive configuration
* [ ] 26.10 Remove development secrets
* [ ] 26.11 Verify production environment variables

---

# 27. Performance

* [ ] 27.1 Optimize item list queries
* [ ] 27.2 Add pagination
* [ ] 27.3 Optimize image loading
* [ ] 27.4 Add image caching
* [ ] 27.5 Optimize search queries
* [ ] 27.6 Optimize realtime subscriptions
* [ ] 27.7 Reduce unnecessary recompositions
* [ ] 27.8 Audit app startup performance
* [ ] 27.9 Audit memory usage

---

# 28. Final UI Polish

* [ ] 28.1 Audit all screens for visual consistency
* [ ] 28.2 Audit typography
* [ ] 28.3 Audit spacing
* [ ] 28.4 Audit icons
* [ ] 28.5 Audit buttons
* [ ] 28.6 Audit cards
* [ ] 28.7 Audit forms
* [ ] 28.8 Audit bottom navigation
* [ ] 28.9 Audit dialogs
* [ ] 28.10 Audit animations
* [ ] 28.11 Audit dark/light theme behavior
* [ ] 28.12 Final UI consistency pass

---

# 29. Release Preparation

* [ ] 29.1 Configure release build
* [ ] 29.2 Configure application ID
* [ ] 29.3 Configure app version
* [ ] 29.4 Create release signing configuration
* [ ] 29.5 Configure production Supabase project
* [ ] 29.6 Configure production security policies
* [ ] 29.7 Remove debug logging
* [ ] 29.8 Build release APK
* [ ] 29.9 Test release APK
* [ ] 29.10 Fix release-only issues
* [ ] 29.11 Create final production build

---

# 30. MVP Completion Audit

* [ ] 30.1 Verify student authentication
* [ ] 30.2 Verify anonymous posting
* [ ] 30.3 Verify lost-item reporting
* [ ] 30.4 Verify found-item reporting
* [ ] 30.5 Verify item categories
* [ ] 30.6 Verify campus locations
* [ ] 30.7 Verify image uploads
* [ ] 30.8 Verify item search
* [ ] 30.9 Verify item filtering
* [ ] 30.10 Verify item details
* [ ] 30.11 Verify claim workflow
* [ ] 30.12 Verify admin status management
* [ ] 30.13 Verify Submitted to DO
* [ ] 30.14 Verify notifications
* [ ] 30.15 Verify mentions
* [ ] 30.16 Verify My Reports
* [ ] 30.17 Verify admin permissions
* [ ] 30.18 Verify RLS security
* [ ] 30.19 Verify critical error states
* [ ] 30.20 Verify final navigation
* [ ] 30.21 Verify final release build

---

# Blocked

* [!] [PLACEHOLDER: task] — waiting on [PLACEHOLDER: what]

---

# Parked

Things deliberately deferred. Not forgotten, not being built.

* [PLACEHOLDER: thing] — revisit after v1
* Push notifications — revisit after core notification system
* In-app messaging — revisit after MVP
* Campus map integration — revisit after v1
* QR code item verification — revisit after v1
* AI-powered item matching — revisit after v1
* Web admin dashboard — revisit after mobile MVP
* Advanced moderation tools — revisit after v1
* Analytics dashboard — revisit after v1
