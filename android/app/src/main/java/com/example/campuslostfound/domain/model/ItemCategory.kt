package com.example.campuslostfound.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.campuslostfound.R

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

    /**
     * Shoes item category.
     */
    data object Shoes : ItemCategory {
        override val id: String = "shoes"
        override val displayNameResId: Int = R.string.category_shoes
        override val iconResId: Int = R.drawable.ic_category_shoes
    }

    /**
     * Blazer item category.
     */
    data object Blazer : ItemCategory {
        override val id: String = "blazer"
        override val displayNameResId: Int = R.string.category_blazer
        override val iconResId: Int = R.drawable.ic_category_blazer
    }

    /**
     * Phone item category.
     */
    data object Phone : ItemCategory {
        override val id: String = "phone"
        override val displayNameResId: Int = R.string.category_phone
        override val iconResId: Int = R.drawable.ic_category_phone
    }

    /**
     * Wallet item category.
     */
    data object Wallet : ItemCategory {
        override val id: String = "wallet"
        override val displayNameResId: Int = R.string.category_wallet
        override val iconResId: Int = R.drawable.ic_category_wallet
    }

    /**
     * School ID item category.
     */
    data object SchoolID : ItemCategory {
        override val id: String = "school_id"
        override val displayNameResId: Int = R.string.category_school_id
        override val iconResId: Int = R.drawable.ic_category_school_id
    }

    /**
     * Jacket item category.
     */
    data object Jacket : ItemCategory {
        override val id: String = "jacket"
        override val displayNameResId: Int = R.string.category_jacket
        override val iconResId: Int = R.drawable.ic_category_jacket
    }

    /**
     * Uniform item category.
     */
    data object Uniform : ItemCategory {
        override val id: String = "uniform"
        override val displayNameResId: Int = R.string.category_uniform
        override val iconResId: Int = R.drawable.ic_category_uniform
    }

    /**
     * Tumbler item category.
     */
    data object Tumbler : ItemCategory {
        override val id: String = "tumbler"
        override val displayNameResId: Int = R.string.category_tumbler
        override val iconResId: Int = R.drawable.ic_category_tumbler
    }

    /**
     * Mini Fan item category.
     */
    data object MiniFan : ItemCategory {
        override val id: String = "mini_fan"
        override val displayNameResId: Int = R.string.category_mini_fan
        override val iconResId: Int = R.drawable.ic_category_mini_fan
    }

    /**
     * Umbrella item category.
     */
    data object Umbrella : ItemCategory {
        override val id: String = "umbrella"
        override val displayNameResId: Int = R.string.category_umbrella
        override val iconResId: Int = R.drawable.ic_category_umbrella
    }

    /**
     * Others item category.
     */
    data object Others : ItemCategory {
        override val id: String = "others"
        override val displayNameResId: Int = R.string.category_others
        override val iconResId: Int = R.drawable.ic_category_others
    }

    companion object {
        /**
         * A complete list of all registered item categories in the system.
         * Future tasks (11.2 - 11.12) will populate this list as categories are implemented.
         */
        val values: List<ItemCategory> = listOf(
            Shoes,
            Blazer,
            Phone,
            Wallet,
            SchoolID,
            Jacket,
            Uniform,
            Tumbler,
            MiniFan,
            Umbrella,
            Others
        )

        /**
         * Safely finds an [ItemCategory] by its persistent unique [id], or null if not found.
         * Useful for database deserialization and mapping database strings back to type-safe domain models.
         */
        fun fromId(id: String): ItemCategory? {
            return values.find { it.id.equals(id, ignoreCase = true) }
        }
    }
}
