package com.example.campuslostfound.data.model

import com.example.campuslostfound.domain.model.AppNotification
import com.example.campuslostfound.domain.model.NotificationType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object (DTO) representing an in-app notification record,
 * mapping directly to the Supabase `notifications` PostgreSQL database table.
 *
 * @property id Primary key (UUID string).
 * @property userId Foreign key referencing `profiles.id` (`user_id`).
 * @property title Concise notification header (`title`).
 * @property message Detailed notification body content (`message`).
 * @property type Notification classification type ("NEW_ITEM", "CLAIM_UPDATE", "MENTION", "SYSTEM") (`type`).
 * @property relatedItemId Optional foreign key referencing `items.id` (`related_item_id`).
 * @property relatedClaimId Optional foreign key referencing `claims.id` (`related_claim_id`).
 * @property isRead Flag indicating if notification has been read (`is_read`).
 * @property createdAt Timestamp string when record was created (`created_at`).
 */
@Serializable
data class AppNotificationDto(
    @SerialName("id")
    val id: String,
    @SerialName("user_id")
    val userId: String,
    @SerialName("title")
    val title: String,
    @SerialName("message")
    val message: String,
    @SerialName("type")
    val type: String = "SYSTEM",
    @SerialName("related_item_id")
    val relatedItemId: String? = null,
    @SerialName("related_claim_id")
    val relatedClaimId: String? = null,
    @SerialName("is_read")
    val isRead: Boolean = false,
    @SerialName("created_at")
    val createdAt: String? = null
)

/**
 * Maps [AppNotificationDto] to the clean architecture domain model [AppNotification].
 */
fun AppNotificationDto.toDomain(): AppNotification {
    return AppNotification(
        id = id,
        userId = userId,
        title = title,
        message = message,
        type = NotificationType.fromString(type),
        relatedItemId = relatedItemId,
        relatedClaimId = relatedClaimId,
        isRead = isRead,
        createdAt = createdAt
    )
}

/**
 * Maps domain model [AppNotification] to the Supabase data transfer object [AppNotificationDto].
 */
fun AppNotification.toDto(): AppNotificationDto {
    return AppNotificationDto(
        id = id,
        userId = userId,
        title = title,
        message = message,
        type = type.name,
        relatedItemId = relatedItemId,
        relatedClaimId = relatedClaimId,
        isRead = isRead,
        createdAt = createdAt
    )
}
