package com.example.campuslostfound.domain.model

/**
 * Domain model representing a campus physical location in the Campus Lost & Found system.
 *
 * Encapsulates building, floor, room, and descriptive landmarks.
 *
 * @property id Unique location identifier (UUID or ID string).
 * @property building Name of the campus building (e.g., "Main Building", "Science Hall", "Library").
 * @property floor Optional floor specification (e.g., "1st Floor", "2nd Floor", "Ground Floor").
 * @property room Optional room or area designation (e.g., "Room 302", "Study Lounge").
 * @property description Optional descriptive landmark or extra notes for precise location.
 * @property createdAt Timestamp string (ISO 8601) when the location was created.
 * @property updatedAt Timestamp string (ISO 8601) when the location was last updated.
 */
data class CampusLocation(
    val id: String,
    val building: String,
    val floor: String? = null,
    val room: String? = null,
    val description: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
) {
    /**
     * Formats the location into a human-readable display string.
     */
    fun getFormattedLocation(): String {
        val parts = listOfNotNull(
            building,
            floor?.takeIf { it.isNotBlank() },
            room?.takeIf { it.isNotBlank() }
        )
        return parts.joinToString(", ")
    }

    companion object {
        /**
         * Standard building options across campus.
         */
        val DEFAULT_BUILDINGS = listOf(
            "Main Building",
            "Science Hall",
            "Engineering Complex",
            "Student Center",
            "Library",
            "Gymnasium",
            "Administration Building",
            "Other/Outside"
        )
    }
}
