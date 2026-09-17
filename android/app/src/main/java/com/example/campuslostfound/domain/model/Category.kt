package com.example.campuslostfound.domain.model

/**
 * Domain model representing an item category in the Campus Lost & Found application.
 *
 * Categories are used to classify lost and found items (e.g., Shoes, Phone, Wallet).
 *
 * @property id Unique category identifier (UUID or ID string).
 * @property name Name of the category (e.g., "Shoes", "Blazer", "Phone", "Wallet", "School ID", etc.).
 * @property iconName Optional icon identifier string associated with the category.
 * @property description Optional detailed description or notes about the category.
 * @property createdAt Timestamp string (ISO 8601) when the category was created.
 * @property updatedAt Timestamp string (ISO 8601) when the category was last updated.
 */
data class Category(
    val id: String,
    val name: String,
    val iconName: String? = null,
    val description: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
) {
    companion object {
        /**
         * Standard category names as specified in Section 11 of execution plan.
         */
        val DEFAULT_CATEGORIES = listOf(
            "Shoes",
            "Blazer",
            "Phone",
            "Wallet",
            "School ID",
            "Jacket",
            "Uniform",
            "Tumbler",
            "Mini Fan",
            "Umbrella",
            "Others"
        )
    }
}
