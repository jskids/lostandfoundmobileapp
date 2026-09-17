package com.example.campuslostfound.domain.model

/**
 * Type of report indicating whether an item was lost or found.
 */
enum class ItemType {
    LOST,
    FOUND;

    companion object {
        fun fromString(value: String?): ItemType {
            return when (value?.uppercase()) {
                "FOUND" -> FOUND
                else -> LOST
            }
        }
    }
}

/**
 * Domain model representing a lost or found item report in the Campus Lost & Found system.
 *
 * @property id Unique item report identifier (UUID).
 * @property userId Foreign key ID of the user who submitted the item report (references [UserProfile.id]).
 * @property title Brief summary title describing the item.
 * @property description Detailed description of the item.
 * @property type Distinguishes whether the item was lost or found ([ItemType]).
 * @property categoryId Optional category identifier for filtering (e.g., Electronics, Attire).
 * @property locationId Optional campus location identifier.
 * @property building Optional building name where the item was lost/found.
 * @property floor Optional floor name/number where the item was lost/found.
 * @property room Optional room name/number where the item was lost/found.
 * @property dateReported Date or timestamp string when the item was lost or found.
 * @property imageUrl Optional Supabase Storage URL for the item image.
 * @property isAnonymous Flag indicating whether the report should be shown anonymously.
 * @property submittedToDO Flag indicating if the physical item or report was officially turned over to the Disciplinary Office (DO) for safekeeping and verification.
 * @property status Current status in the item resolution lifecycle ([ItemStatus]).
 * @property createdAt Timestamp string when the item report was created.
 * @property updatedAt Timestamp string when the item report was last updated.
 */
data class LostFoundItem(
    val id: String,
    val userId: String,
    val title: String,
    val description: String,
    val type: ItemType,
    val categoryId: String? = null,
    val locationId: String? = null,
    val building: String? = null,
    val floor: String? = null,
    val room: String? = null,
    val dateReported: String? = null,
    val imageUrl: String? = null,
    val isAnonymous: Boolean = false,
    val submittedToDO: Boolean = false,
    val status: ItemStatus = ItemStatus.UNSETTLED,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
