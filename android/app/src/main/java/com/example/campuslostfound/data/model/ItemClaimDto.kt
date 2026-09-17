package com.example.campuslostfound.data.model

import com.example.campuslostfound.domain.model.ClaimStatus
import com.example.campuslostfound.domain.model.ItemClaim
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object (DTO) representing an item claim record,
 * mapping directly to the Supabase `claims` PostgreSQL database table.
 *
 * @property id Primary key (UUID string).
 * @property itemId Foreign key referencing `items.id` (`item_id`).
 * @property claimerId Foreign key referencing `profiles.id` (`claimer_id`).
 * @property proofDescription Explanation or proof provided by the claimer (`proof_description`).
 * @property proofImageUrl Optional image URL pointing to proof uploaded in Supabase Storage (`proof_image_url`).
 * @property status Claim verification status: "PENDING", "APPROVED", "REJECTED", or "CANCELLED" (`status`).
 * @property adminNotes Optional administrative verification/rejection notes (`admin_notes`).
 * @property createdAt Timestamp string when the claim was submitted (`created_at`).
 * @property updatedAt Timestamp string when the claim was last updated (`updated_at`).
 */
@Serializable
data class ItemClaimDto(
    @SerialName("id")
    val id: String,
    @SerialName("item_id")
    val itemId: String,
    @SerialName("claimer_id")
    val claimerId: String,
    @SerialName("proof_description")
    val proofDescription: String,
    @SerialName("proof_image_url")
    val proofImageUrl: String? = null,
    @SerialName("status")
    val status: String = "PENDING",
    @SerialName("admin_notes")
    val adminNotes: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null
)

/**
 * Maps [ItemClaimDto] to the clean architecture domain model [ItemClaim].
 */
fun ItemClaimDto.toDomain(): ItemClaim {
    return ItemClaim(
        id = id,
        itemId = itemId,
        claimerId = claimerId,
        proofDescription = proofDescription,
        proofImageUrl = proofImageUrl,
        status = ClaimStatus.fromString(status),
        adminNotes = adminNotes,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

/**
 * Maps domain model [ItemClaim] to the Supabase data transfer object [ItemClaimDto].
 */
fun ItemClaim.toDto(): ItemClaimDto {
    return ItemClaimDto(
        id = id,
        itemId = itemId,
        claimerId = claimerId,
        proofDescription = proofDescription,
        proofImageUrl = proofImageUrl,
        status = status.name,
        adminNotes = adminNotes,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
