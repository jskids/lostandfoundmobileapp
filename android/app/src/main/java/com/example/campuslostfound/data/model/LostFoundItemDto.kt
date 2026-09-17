package com.example.campuslostfound.data.model

import com.example.campuslostfound.domain.model.ItemStatus
import com.example.campuslostfound.domain.model.ItemType
import com.example.campuslostfound.domain.model.LostFoundItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object (DTO) representing a lost or found item record,
 * mapping directly to the Supabase `items` PostgreSQL database table.
 *
 * @property id Primary key (UUID string).
 * @property userId Foreign key referencing `profiles.id` (`user_id`).
 * @property title Brief item summary title (`title`).
 * @property description Detailed item description (`description`).
 * @property type Item classification: "LOST" or "FOUND" (`type`).
 * @property categoryId Foreign key ID for item category (`category_id`).
 * @property locationId Foreign key ID for campus location (`location_id`).
 * @property building Building name where item was lost/found (`building`).
 * @property floor Floor level where item was lost/found (`floor`).
 * @property room Specific room details (`room`).
 * @property dateReported Date/timestamp when item was lost or found (`date_reported`).
 * @property imageUrl URL pointing to uploaded image in Supabase Storage (`image_url`).
 * @property isAnonymous Flag for anonymous posting (`is_anonymous`).
 * @property submittedToDO Boolean flag (`submitted_to_do`) indicating if the item has been turned over to the Disciplinary Office (DO) for official safekeeping.
 * @property status Workflow status: "UNSETTLED", "PENDING", or "CLAIMED" (`status`).
 * @property createdAt Timestamp string when record was created (`created_at`).
 * @property updatedAt Timestamp string when record was last updated (`updated_at`).
 */
@Serializable
data class LostFoundItemDto(
    @SerialName("id")
    val id: String,
    @SerialName("user_id")
    val userId: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("type")
    val type: String,
    @SerialName("category_id")
    val categoryId: String? = null,
    @SerialName("location_id")
    val locationId: String? = null,
    @SerialName("building")
    val building: String? = null,
    @SerialName("floor")
    val floor: String? = null,
    @SerialName("room")
    val room: String? = null,
    @SerialName("date_reported")
    val dateReported: String? = null,
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("is_anonymous")
    val isAnonymous: Boolean = false,
    @SerialName("submitted_to_do")
    val submittedToDO: Boolean = false,
    @SerialName("status")
    val status: String = "UNSETTLED",
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null
)

/**
 * Maps [LostFoundItemDto] to the clean architecture domain model [LostFoundItem].
 */
fun LostFoundItemDto.toDomain(): LostFoundItem {
    return LostFoundItem(
        id = id,
        userId = userId,
        title = title,
        description = description,
        type = ItemType.fromString(type),
        categoryId = categoryId,
        locationId = locationId,
        building = building,
        floor = floor,
        room = room,
        dateReported = dateReported,
        imageUrl = imageUrl,
        isAnonymous = isAnonymous,
        submittedToDO = submittedToDO,
        status = ItemStatus.fromString(status),
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

/**
 * Maps domain model [LostFoundItem] to the Supabase data transfer object [LostFoundItemDto].
 */
fun LostFoundItem.toDto(): LostFoundItemDto {
    return LostFoundItemDto(
        id = id,
        userId = userId,
        title = title,
        description = description,
        type = type.name,
        categoryId = categoryId,
        locationId = locationId,
        building = building,
        floor = floor,
        room = room,
        dateReported = dateReported,
        imageUrl = imageUrl,
        isAnonymous = isAnonymous,
        submittedToDO = submittedToDO,
        status = status.name,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
