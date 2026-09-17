package com.example.campuslostfound.domain.model

import androidx.annotation.StringRes
import com.example.campuslostfound.R

/**
 * Sealed interface representing type-safe campus building locations in the Campus Lost & Found system.
 *
 * Centralizing building data in the domain layer ensures consistent campus location tracking
 * across forms, filtering, details, and persistence.
 */
sealed interface Building {
    /**
     * Unique string identifier for the building (persisted in database/Supabase).
     */
    val id: String

    /**
     * Short building code (e.g., "MAB", "SC", "CL").
     */
    val code: String

    /**
     * Resource ID for the user-friendly building display name.
     */
    @get:StringRes
    val nameResId: Int

    /**
     * Main Academic Building.
     */
    data object MainAcademic : Building {
        override val id: String = "main_academic"
        override val code: String = "MAB"
        override val nameResId: Int = R.string.building_main_academic
    }

    /**
     * Science Complex.
     */
    data object ScienceComplex : Building {
        override val id: String = "science_complex"
        override val code: String = "SC"
        override val nameResId: Int = R.string.building_science_complex
    }

    /**
     * Central Library.
     */
    data object CentralLibrary : Building {
        override val id: String = "central_library"
        override val code: String = "CL"
        override val nameResId: Int = R.string.building_central_library
    }

    /**
     * Student Center.
     */
    data object StudentCenter : Building {
        override val id: String = "student_center"
        override val code: String = "SUB"
        override val nameResId: Int = R.string.building_student_center
    }

    /**
     * Engineering Hall.
     */
    data object EngineeringHall : Building {
        override val id: String = "engineering_hall"
        override val code: String = "EH"
        override val nameResId: Int = R.string.building_engineering_hall
    }

    /**
     * Gymnasium & Sports Center.
     */
    data object Gymnasium : Building {
        override val id: String = "gymnasium"
        override val code: String = "GYM"
        override val nameResId: Int = R.string.building_gymnasium
    }

    /**
     * Administration Building.
     */
    data object Administration : Building {
        override val id: String = "administration"
        override val code: String = "ADMIN"
        override val nameResId: Int = R.string.building_administration
    }

    /**
     * Campus Cafeteria.
     */
    data object Cafeteria : Building {
        override val id: String = "cafeteria"
        override val code: String = "CAF"
        override val nameResId: Int = R.string.building_cafeteria
    }

    companion object {
        /**
         * Complete list of all registered campus buildings in the system.
         */
        val values: List<Building> = listOf(
            MainAcademic,
            ScienceComplex,
            CentralLibrary,
            StudentCenter,
            EngineeringHall,
            Gymnasium,
            Administration,
            Cafeteria
        )

        /**
         * Safely finds a [Building] by its persistent unique [id], or null if not found.
         */
        fun fromId(id: String): Building? {
            return values.find { it.id.equals(id, ignoreCase = true) }
        }

        /**
         * Safely finds a [Building] by its short building [code], or null if not found.
         */
        fun fromCode(code: String): Building? {
            return values.find { it.code.equals(code, ignoreCase = true) }
        }
    }
}
