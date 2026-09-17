package com.example.campuslostfound.domain.model

/**
 * Type-safe domain model representing room or specific sub-location areas in the Campus Lost & Found system.
 *
 * Centralizing room representation in the domain layer enables consistent location tracking,
 * string formatting, input validation, and persistence.
 */
data class Room(
    val id: String,
    val name: String,
    val number: String? = null,
    val buildingId: String? = null,
    val floorId: String? = null
) {
    /**
     * Display title for the room (e.g., "Room 101", "Computer Lab 1", "Main Lobby").
     */
    val displayTitle: String
        get() {
            val trimmedName = name.trim()
            val trimmedNumber = number?.trim()
            return when {
                !trimmedNumber.isNullOrEmpty() && trimmedName.isEmpty() -> "Room $trimmedNumber"
                !trimmedNumber.isNullOrEmpty() && !trimmedName.contains(trimmedNumber, ignoreCase = true) -> "$trimmedName ($trimmedNumber)"
                else -> trimmedName
            }
        }

    /**
     * Formats the location display combining building code, floor code, and room title/number.
     * Example: "MAB - 1F - Room 101"
     */
    fun getFormattedLocation(building: Building? = null, floor: Floor? = null): String {
        val parts = mutableListOf<String>()
        building?.let { parts.add(it.code) }
        floor?.let { parts.add(it.code) }
        if (displayTitle.isNotEmpty()) {
            parts.add(displayTitle)
        }
        return parts.joinToString(" - ")
    }

    companion object {
        /**
         * Common campus room presets across various buildings.
         */
        val MainLobby = Room(id = "main_lobby", name = "Main Lobby")
        val StudyHall = Room(id = "study_hall", name = "Study Hall")
        val ComputerLab1 = Room(id = "comp_lab_1", name = "Computer Lab 1", number = "101")
        val ComputerLab2 = Room(id = "comp_lab_2", name = "Computer Lab 2", number = "102")
        val LibraryReadingRoom = Room(id = "lib_reading_room", name = "Reading Room")
        val DeansOffice = Room(id = "deans_office", name = "Dean's Office")
        val StudentLounge = Room(id = "student_lounge", name = "Student Lounge")
        val FoodCourt = Room(id = "food_court", name = "Food Court")
        val GymnasiumCourt = Room(id = "gym_court", name = "Main Court")

        /**
         * List of standard preset rooms available in the system.
         */
        val presets: List<Room> = listOf(
            MainLobby,
            StudyHall,
            ComputerLab1,
            ComputerLab2,
            LibraryReadingRoom,
            DeansOffice,
            StudentLounge,
            FoodCourt,
            GymnasiumCourt
        )

        /**
         * Safely finds a preset [Room] by its persistent unique [id], or null if not found.
         */
        fun fromId(id: String): Room? {
            return presets.find { it.id.equals(id, ignoreCase = true) }
        }

        /**
         * Creates a custom [Room] instance from user text input with auto-generated ID.
         */
        fun fromCustomInput(input: String, buildingId: String? = null, floorId: String? = null): Room {
            val trimmed = input.trim()
            val sanitizedId = trimmed.lowercase()
                .replace(Regex("[^a-z0-9]+"), "_")
                .trim('_')
            return Room(
                id = if (sanitizedId.isEmpty()) "custom_room" else "room_$sanitizedId",
                name = trimmed,
                number = if (trimmed.matches(Regex("^[0-9]+[A-Za-z]?$"))) trimmed else null,
                buildingId = buildingId,
                floorId = floorId
            )
        }
    }
}
