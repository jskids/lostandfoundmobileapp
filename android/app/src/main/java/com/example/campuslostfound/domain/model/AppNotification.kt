package com.example.campuslostfound.domain.model

/**
 * Classification category of an in-app notification.
 */
enum class NotificationType {
    NEW_ITEM,
    CLAIM_UPDATE,
    MENTION,
    SYSTEM;

    companion object {
        fun fromString(value: String?): NotificationType {
            return when (value?.uppercase()) {
                "NEW_ITEM" -> NEW_ITEM
                "CLAIM_UPDATE" -> CLAIM_UPDATE
                "MENTION" -> MENTION
                else -> SYSTEM
            }
        }
    }
}

/**
 * Domain model representing an in-app notification in the Campus Lost & Found system.
 *
 * @property id Unique notification identifier (UUID).
 * @property userId Recipient student user ID (references [UserProfile.id]).
 * @property title Concise notification header/title.
 * @property message Detailed body description of the notification.
 * @property type Category classification of notification ([NotificationType]).
 * @property relatedItemId Optional associated item ID (references [LostFoundItem.id]).
 * @property relatedClaimId Optional associated claim ID (references [ItemClaim.id]).
 * @property isRead Flag indicating whether the notification has been opened/read by the user.
 * @property createdAt Timestamp string (ISO 8601) when the notification was created.
 */
data class AppNotification(
    val id: String,
    val userId: String,
    val title: String,
    val message: String,
    val type: NotificationType = NotificationType.SYSTEM,
    val relatedItemId: String? = null,
    val relatedClaimId: String? = null,
    val isRead: Boolean = false,
    val createdAt: String? = null
)
