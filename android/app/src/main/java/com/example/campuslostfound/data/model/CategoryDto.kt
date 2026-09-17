package com.example.campuslostfound.data.model

import com.example.campuslostfound.domain.model.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object (DTO) representing an item category record,
 * mapping directly to the Supabase `categories` PostgreSQL database table.
 *
 * @property id Primary key (UUID string or unique identifier).
 * @property name Display name of the item category (`name`).
 * @property iconName Optional icon identifier string (`icon_name`).
 * @property description Optional description of the category (`description`).
 * @property createdAt Timestamp string when the record was created (`created_at`).
 * @property updatedAt Timestamp string when the record was last updated (`updated_at`).
 */
@Serializable
data class CategoryDto(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("icon_name")
    val iconName: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null
)

/**
 * Maps [CategoryDto] to the clean architecture domain model [Category].
 */
fun CategoryDto.toDomain(): Category {
    return Category(
        id = id,
        name = name,
        iconName = iconName,
        description = description,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

/**
 * Maps domain model [Category] to the Supabase data transfer object [CategoryDto].
 */
fun Category.toDto(): CategoryDto {
    return CategoryDto(
        id = id,
        name = name,
        iconName = iconName,
        description = description,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
