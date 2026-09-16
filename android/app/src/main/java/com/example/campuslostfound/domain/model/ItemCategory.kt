package com.example.campuslostfound.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Sealed interface representing the type-safe structure for all item categories in the Campus Lost & Found system.
 *
 * Centralizing category definitions here ensures type safety across the domain, data, and presentation layers.
 * It prevents scattered category strings, simplifies localization, and integrates cleanly with Jetpack Compose UI
 * and Supabase persistence.
 *
 * Each concrete category will be implemented in subsequent tasks (11.2 - 11.12) as a nested object/class or
 * standard subclass implementing this interface.
 */
sealed interface ItemCategory {
    /**
     * Unique string identifier for the category. This ID is persisted in the database (Supabase)
     * and used for network serialization/deserialization.
     */
    val id: String

    /**
     * Resource ID for the translated user-friendly display name of the category.
     */
    @get:StringRes
    val displayNameResId: Int

    /**
     * Optional Resource ID for the vector drawable icon of the category.
     */
    @get:DrawableRes
    val iconResId: Int?

    companion object {
        /**
         * A complete list of all registered item categories in the system.
         * Future tasks (11.2 - 11.12) will populate this list as categories are implemented.
         * For Task 11.1, the container structure is established, but concrete entries are deferred.
         */
        val values: List<ItemCategory> = emptyList()

        /**
         * Safely finds an [ItemCategory] by its persistent unique [id], or null if not found.
         * Useful for database deserialization and mapping database strings back to type-safe domain models.
         */
        fun fromId(id: String): ItemCategory? {
            return values.find { it.id.equals(id, ignoreCase = true) }
        }
    }
}
