package com.example.campuslostfound.domain.model

/**
 * Lifecycle workflow status of a reported lost or found item.
 *
 * Enforces status progression rules and administrative permission requirements.
 *
 * @property label Human-readable UI display label for the status.
 * @property description Detailed explanation of what the status signifies in the item resolution workflow.
 * @property isAdminOnly Flag indicating if updating to this status requires administrator permissions.
 */
enum class ItemStatus(
    val label: String,
    val description: String,
    val isAdminOnly: Boolean = false
) {
    /**
     * Newly reported item awaiting claim or resolution.
     */
    UNSETTLED(
        label = "Unsettled",
        description = "Item report is active and pending matching or claim submission.",
        isAdminOnly = false
    ),

    /**
     * A claim has been filed and is undergoing administrative/DO verification.
     */
    PENDING(
        label = "Pending",
        description = "A claim has been filed for this item and is currently under verification.",
        isAdminOnly = true
    ),

    /**
     * Ownership verified and item successfully handed over to owner.
     */
    CLAIMED(
        label = "Claimed",
        description = "Item ownership verified and successfully returned to owner.",
        isAdminOnly = true
    );

    companion object {
        /**
         * Safely parses a string identifier into an [ItemStatus] enum value.
         */
        fun fromString(value: String?): ItemStatus {
            return when (value?.uppercase()) {
                "PENDING" -> PENDING
                "CLAIMED" -> CLAIMED
                else -> UNSETTLED
            }
        }
    }
}
