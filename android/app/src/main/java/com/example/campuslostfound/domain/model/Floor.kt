package com.example.campuslostfound.domain.model

import androidx.annotation.StringRes
import com.example.campuslostfound.R

/**
 * Sealed interface representing type-safe campus building floor levels in the Campus Lost & Found system.
 *
 * Centralizing floor data in the domain layer ensures consistent location level tracking
 * across report forms, filter criteria, item details, and persistence.
 */
sealed interface Floor {
    /**
     * Unique string identifier for the floor (persisted in database/Supabase).
     */
    val id: String

    /**
     * Numeric level representation for spatial sorting or level comparisons (-1 for Basement, 1..5, 6 for Roof Deck).
     */
    val level: Int

    /**
     * Short display code (e.g., "B1", "1F", "2F", "RF").
     */
    val code: String

    /**
     * Resource ID for the user-friendly floor display name.
     */
    @get:StringRes
    val nameResId: Int

    /**
     * Basement / Lower Ground Floor.
     */
    data object Basement : Floor {
        override val id: String = "basement"
        override val level: Int = -1
        override val code: String = "B1"
        override val nameResId: Int = R.string.floor_basement
    }

    /**
     * 1st Floor / Ground Floor.
     */
    data object FirstFloor : Floor {
        override val id: String = "floor_1"
        override val level: Int = 1
        override val code: String = "1F"
        override val nameResId: Int = R.string.floor_1
    }

    /**
     * 2nd Floor.
     */
    data object SecondFloor : Floor {
        override val id: String = "floor_2"
        override val level: Int = 2
        override val code: String = "2F"
        override val nameResId: Int = R.string.floor_2
    }

    /**
     * 3rd Floor.
     */
    data object ThirdFloor : Floor {
        override val id: String = "floor_3"
        override val level: Int = 3
        override val code: String = "3F"
        override val nameResId: Int = R.string.floor_3
    }

    /**
     * 4th Floor.
     */
    data object FourthFloor : Floor {
        override val id: String = "floor_4"
        override val level: Int = 4
        override val code: String = "4F"
        override val nameResId: Int = R.string.floor_4
    }

    /**
     * 5th Floor.
     */
    data object FifthFloor : Floor {
        override val id: String = "floor_5"
        override val level: Int = 5
        override val code: String = "5F"
        override val nameResId: Int = R.string.floor_5
    }

    /**
     * Roof Deck / Top Level.
     */
    data object RoofDeck : Floor {
        override val id: String = "roof_deck"
        override val level: Int = 6
        override val code: String = "RF"
        override val nameResId: Int = R.string.floor_roof_deck
    }

    companion object {
        /**
         * Complete list of all registered campus floors ordered from lowest to highest.
         */
        val values: List<Floor> = listOf(
            Basement,
            FirstFloor,
            SecondFloor,
            ThirdFloor,
            FourthFloor,
            FifthFloor,
            RoofDeck
        )

        /**
         * Safely finds a [Floor] by its persistent unique [id], or null if not found.
         */
        fun fromId(id: String): Floor? {
            return values.find { it.id.equals(id, ignoreCase = true) }
        }

        /**
         * Safely finds a [Floor] by its numeric [level], or null if not found.
         */
        fun fromLevel(level: Int): Floor? {
            return values.find { it.level == level }
        }

        /**
         * Safely finds a [Floor] by its short display [code], or null if not found.
         */
        fun fromCode(code: String): Floor? {
            return values.find { it.code.equals(code, ignoreCase = true) }
        }
    }
}
