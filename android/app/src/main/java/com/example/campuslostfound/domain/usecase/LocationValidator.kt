package com.example.campuslostfound.domain.usecase

import androidx.annotation.StringRes
import com.example.campuslostfound.R
import com.example.campuslostfound.domain.model.Building
import com.example.campuslostfound.domain.model.Floor

/**
 * Encapsulates the result of location field validation.
 */
data class LocationValidationResult(
    val isValid: Boolean,
    @get:StringRes val buildingErrorResId: Int? = null,
    @get:StringRes val floorErrorResId: Int? = null,
    @get:StringRes val roomErrorResId: Int? = null
)

/**
 * Domain use case object for validating campus building, floor level, and room location inputs.
 */
object LocationValidator {

    const val MAX_ROOM_LENGTH = 50

    /**
     * Validates campus location selection inputs.
     *
     * @param building Selected [Building] instance, or null.
     * @param floor Selected [Floor] instance, or null.
     * @param roomText User input or preset room text, or null.
     * @param isRoomRequired Whether room/area description is strictly required.
     * @return [LocationValidationResult] indicating validity and specific string resource error IDs.
     */
    fun validate(
        building: Building?,
        floor: Floor?,
        roomText: String?,
        isRoomRequired: Boolean = false
    ): LocationValidationResult {
        val buildingError = if (building == null) {
            R.string.error_building_required
        } else {
            null
        }

        val floorError = if (floor == null) {
            R.string.error_floor_required
        } else {
            null
        }

        val trimmedRoom = roomText?.trim().orEmpty()
        val roomError = when {
            isRoomRequired && trimmedRoom.isEmpty() -> R.string.error_room_required
            trimmedRoom.length > MAX_ROOM_LENGTH -> R.string.error_room_too_long
            else -> null
        }

        val isValid = buildingError == null && floorError == null && roomError == null

        return LocationValidationResult(
            isValid = isValid,
            buildingErrorResId = buildingError,
            floorErrorResId = floorError,
            roomErrorResId = roomError
        )
    }

    /**
     * Overload accepting raw persistent ID inputs.
     */
    fun validateFromIds(
        buildingId: String?,
        floorId: String?,
        roomText: String?,
        isRoomRequired: Boolean = false
    ): LocationValidationResult {
        val building = buildingId?.takeIf { it.isNotBlank() }?.let { Building.fromId(it) ?: Building.fromCode(it) }
        val floor = floorId?.takeIf { it.isNotBlank() }?.let { Floor.fromId(it) ?: Floor.fromCode(it) }
        return validate(building, floor, roomText, isRoomRequired)
    }
}
