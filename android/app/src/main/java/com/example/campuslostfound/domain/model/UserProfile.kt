package com.example.campuslostfound.domain.model

/**
 * Represents the role of a user within the campus system.
 */
enum class UserRole {
    STUDENT,
    ADMIN,
    STAFF;

    companion object {
        fun fromString(value: String?): UserRole {
            return when (value?.lowercase()) {
                "admin" -> ADMIN
                "staff" -> STAFF
                else -> STUDENT
            }
        }
    }
}

/**
 * Domain model representing a user profile in the Campus Lost & Found application.
 *
 * This clean architecture domain model contains core business attributes and logic
 * for user identity, contact details, role permissions, and user preferences.
 *
 * @property id Unique identifier matching the Supabase Auth user ID (UUID).
 * @property fullName User's full name or display name.
 * @property email Student or campus email address.
 * @property studentId Optional campus student or employee identification number.
 * @property phoneNumber Optional contact phone number for item claim verification.
 * @property avatarUrl Optional URL pointing to the user's avatar image in storage.
 * @property role Role assigned to the user (e.g., STUDENT, ADMIN, STAFF).
 * @property isAnonymousDefault User's default preference for submitting reports anonymously.
 * @property createdAt Timestamp (ISO 8601) when the user profile was created.
 * @property updatedAt Timestamp (ISO 8601) when the user profile was last updated.
 */
data class UserProfile(
    val id: String,
    val fullName: String,
    val email: String,
    val studentId: String? = null,
    val phoneNumber: String? = null,
    val avatarUrl: String? = null,
    val role: UserRole = UserRole.STUDENT,
    val isAnonymousDefault: Boolean = false,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
