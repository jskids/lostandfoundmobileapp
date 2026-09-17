package com.example.campuslostfound.domain.usecase

import com.example.campuslostfound.R
import com.example.campuslostfound.domain.model.Building
import com.example.campuslostfound.domain.model.Floor
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class LocationValidatorTest {

    @Test
    fun `validate returns valid result when building and floor are provided`() {
        val building = Building.MainAcademic
        val floor = Floor.FirstFloor
        val roomText = "Room 101"

        val result = LocationValidator.validate(building, floor, roomText)

        assertTrue(result.isValid)
        assertNull(result.buildingErrorResId)
        assertNull(result.floorErrorResId)
        assertNull(result.roomErrorResId)
    }

    @Test
    fun `validate returns error when building is missing`() {
        val floor = Floor.SecondFloor

        val result = LocationValidator.validate(building = null, floor = floor, roomText = "102")

        assertFalse(result.isValid)
        assertEquals(R.string.error_building_required, result.buildingErrorResId)
        assertNull(result.floorErrorResId)
    }

    @Test
    fun `validate returns error when floor is missing`() {
        val building = Building.ScienceComplex

        val result = LocationValidator.validate(building = building, floor = null, roomText = "Lab 1")

        assertFalse(result.isValid)
        assertNull(result.buildingErrorResId)
        assertEquals(R.string.error_floor_required, result.floorErrorResId)
    }

    @Test
    fun `validate returns error when required room is blank`() {
        val building = Building.CentralLibrary
        val floor = Floor.ThirdFloor

        val result = LocationValidator.validate(
            building = building,
            floor = floor,
            roomText = "   ",
            isRoomRequired = true
        )

        assertFalse(result.isValid)
        assertEquals(R.string.error_room_required, result.roomErrorResId)
    }

    @Test
    fun `validate returns error when room text exceeds 50 characters`() {
        val building = Building.EngineeringHall
        val floor = Floor.FourthFloor
        val longRoomText = "A".repeat(51)

        val result = LocationValidator.validate(
            building = building,
            floor = floor,
            roomText = longRoomText,
            isRoomRequired = false
        )

        assertFalse(result.isValid)
        assertEquals(R.string.error_room_too_long, result.roomErrorResId)
    }

    @Test
    fun `validateFromIds validates raw string IDs correctly`() {
        val result = LocationValidator.validateFromIds(
            buildingId = "main_academic",
            floorId = "floor_1",
            roomText = "Main Lobby"
        )

        assertTrue(result.isValid)
        assertNull(result.buildingErrorResId)
        assertNull(result.floorErrorResId)
        assertNull(result.roomErrorResId)
    }

    @Test
    fun `validateFromIds detects missing building ID`() {
        val result = LocationValidator.validateFromIds(
            buildingId = "",
            floorId = "floor_1",
            roomText = "Main Lobby"
        )

        assertFalse(result.isValid)
        assertEquals(R.string.error_building_required, result.buildingErrorResId)
    }
}
