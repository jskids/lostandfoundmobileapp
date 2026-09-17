package com.example.campuslostfound.data.model

import com.example.campuslostfound.domain.model.UserProfile
import com.example.campuslostfound.domain.model.UserRole
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object (DTO) for the user profile, mapping directly to the
 * Supabase `profiles` PostgreSQL database table.
 *
 * @property id Primary key matching the Supabase Auth user ID (`auth.users.id`).
 * @property fullName User's full display name (`full_name`).
 * @property email Student/campus email address (`email`).
 * @property studentId Campus student or employee ID number (`student_id`).
 * @property phoneNumber Contact telephone number (`phone_number`).
 * @property avatarUrl URL pointing to the user's uploaded avatar (`avatar_url`).
 * @property role User role string representation ("student", "admin", "staff") (`role`).
 * @property isAnonymousDefault User's default anonymous posting preference (`is_anonymous_default`).
 * @property createdAt Timestamp string when the profile was inserted (`created_at`).
 * @property updatedAt Timestamp string when the profile was last modified (`updated_at`).
 */
@Serializable
data class UserProfileDto(
    @SerialName("id")
    val id: String,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("email")
    val email: String,
    @SerialName("student_id")
    val studentId: String? = null,
    @SerialName("phone_number")
    val phoneNumber: String? = null,
    @SerialName("avatar_url")
    val avatarUrl: String? = null,
    @SerialName("role")
    val role: String = "student",
    @SerialName("is_anonymous_default")
    val isAnonymousDefault: Boolean = false,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null
)

/**
 * Maps [UserProfileDto] to the clean architecture domain model [UserProfile].
 */
fun UserProfileDto.toDomain(): UserProfile {
    return UserProfile(
        id = id,
        fullName = fullName,
        email = email,
        studentId = studentId,
        phoneNumber = phoneNumber,
        avatarUrl = avatarUrl,
        role = UserRole.fromString(role),
        isAnonymousDefault = isAnonymousDefault,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

/**
 * Maps [UserProfile] domain model to the Supabase data transfer object [UserProfileDto].
 */
fun UserProfile.toDto(): UserProfileDto {
    return UserProfileDto(
        id = id,
        fullName = fullName,
        email = email,
        studentId = studentId,
        phoneNumber = phoneNumber,
        avatarUrl = avatarUrl,
        role = role.name.lowercase(),
        isAnonymousDefault = isAnonymousDefault,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
