package com.example.campuslostfound.domain.model

/**
 * Status of an item claim request during its approval lifecycle.
 */
enum class ClaimStatus {
    PENDING,
    APPROVED,
    REJECTED,
    CANCELLED;

    companion object {
        fun fromString(value: String?): ClaimStatus {
            return when (value?.uppercase()) {
                "APPROVED" -> APPROVED
                "REJECTED" -> REJECTED
                "CANCELLED" -> CANCELLED
                else -> PENDING
            }
        }
    }
}

/**
 * Domain model representing a claim request filed for a lost or found item.
 *
 * @property id Unique claim request identifier (UUID).
 * @property itemId Identifier of the item being claimed (references [LostFoundItem.id]).
 * @property claimerId User ID of the student submitting the claim (references [UserProfile.id]).
 * @property proofDescription Detailed description or proof provided by the claimer.
 * @property proofImageUrl Optional image URL supporting ownership proof stored in Supabase Storage.
 * @property status Current verification status of the claim ([ClaimStatus]).
 * @property adminNotes Optional administrative verification notes or rejection reason.
 * @property createdAt Timestamp string (ISO 8601) when the claim request was created.
 * @property updatedAt Timestamp string (ISO 8601) when the claim request was last updated.
 */
data class ItemClaim(
    val id: String,
    val itemId: String,
    val claimerId: String,
    val proofDescription: String,
    val proofImageUrl: String? = null,
    val status: ClaimStatus = ClaimStatus.PENDING,
    val adminNotes: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
