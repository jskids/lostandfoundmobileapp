package com.example.campuslostfound.data.model

import com.example.campuslostfound.domain.model.CampusLocation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object (DTO) representing a campus location record,
 * mapping directly to the Supabase `locations` PostgreSQL database table.
 *
 * @property id Primary key (UUID string or unique identifier).
 * @property building Building name (`building`).
 * @property floor Optional floor designation (`floor`).
 * @property room Optional room or room number (`room`).
 * @property description Optional detailed notes or landmark description (`description`).
 * @property createdAt Timestamp string when the record was created (`created_at`).
 * @property updatedAt Timestamp string when the record was last updated (`updated_at`).
 */
@Serializable
data class CampusLocationDto(
    @SerialName("id")
    val id: String,
    @SerialName("building")
    val building: String,
    @SerialName("floor")
    val floor: String? = null,
    @SerialName("room")
    val room: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null
)

/**
 * Maps [CampusLocationDto] to the clean architecture domain model [CampusLocation].
 */
fun CampusLocationDto.toDomain(): CampusLocation {
    return CampusLocation(
        id = id,
        building = building,
        floor = floor,
        room = room,
        description = description,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

/**
 * Maps domain model [CampusLocation] to the Supabase data transfer object [CampusLocationDto].
 */
fun CampusLocation.toDto(): CampusLocationDto {
    return CampusLocationDto(
        id = id,
        building = building,
        floor = floor,
        room = room,
        description = description,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
