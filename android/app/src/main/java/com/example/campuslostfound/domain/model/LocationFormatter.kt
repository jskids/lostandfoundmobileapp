package com.example.campuslostfound.domain.model

/**
 * Domain utility providing centralized, type-safe formatting for campus building, floor,
 * and room location combinations across the Campus Lost & Found application.
 */
object LocationFormatter {

    private const val DEFAULT_SEPARATOR = " • "
    private const val SHORT_SEPARATOR = " - "
    private const val UNSPECIFIED_LOCATION = "Unspecified Location"

    /**
     * Formats a complete campus location into a human-readable display string using [Building], [Floor], and [Room].
     * Example: "MAB • 1F • Computer Lab 1 (101)" or "Main Academic Building • 1st Floor"
     */
    fun formatFull(
        building: Building?,
        floor: Floor?,
        room: Room?,
        separator: String = DEFAULT_SEPARATOR
    ): String {
        return formatFull(
            buildingLabel = building?.code,
            floorLabel = floor?.code,
            roomLabel = room?.displayTitle,
            separator = separator
        )
    }

    /**
     * Formats a complete campus location into a human-readable display string using optional string labels.
     */
    fun formatFull(
        buildingLabel: String?,
        floorLabel: String?,
        roomLabel: String?,
        separator: String = DEFAULT_SEPARATOR
    ): String {
        val parts = mutableListOf<String>()

        buildingLabel?.trim()?.takeIf { it.isNotEmpty() }?.let { parts.add(it) }
        floorLabel?.trim()?.takeIf { it.isNotEmpty() }?.let { parts.add(it) }
        roomLabel?.trim()?.takeIf { it.isNotEmpty() }?.let { parts.add(it) }

        return if (parts.isNotEmpty()) {
            parts.joinToString(separator)
        } else {
            UNSPECIFIED_LOCATION
        }
    }

    /**
     * Formats a concise campus location string using short codes, ideal for compact badges and cards.
     * Example: "MAB - 1F - Room 101"
     */
    fun formatShort(
        building: Building?,
        floor: Floor?,
        room: Room?,
        separator: String = SHORT_SEPARATOR
    ): String {
        return formatFull(
            buildingLabel = building?.code,
            floorLabel = floor?.code,
            roomLabel = room?.displayTitle,
            separator = separator
        )
    }

    /**
     * Formats a concise campus location string using string labels and short separators.
     */
    fun formatShort(
        buildingCode: String?,
        floorCode: String?,
        roomTitle: String?,
        separator: String = SHORT_SEPARATOR
    ): String {
        return formatFull(
            buildingLabel = buildingCode,
            floorLabel = floorCode,
            roomLabel = roomTitle,
            separator = separator
        )
    }

    /**
     * Resolves domain entities from persistent IDs and formats a campus location string.
     */
    fun formatFromIds(
        buildingId: String?,
        floorId: String?,
        roomInputOrId: String?,
        shortFormat: Boolean = true
    ): String {
        val building = buildingId?.let { Building.fromId(it) ?: Building.fromCode(it) }
        val floor = floorId?.let { Floor.fromId(it) ?: Floor.fromCode(it) }
        val room = roomInputOrId?.let { input ->
            Room.fromId(input) ?: Room.fromCustomInput(input, buildingId, floorId)
        }

        return if (shortFormat) {
            formatShort(building, floor, room)
        } else {
            formatFull(building, floor, room)
        }
    }
}
