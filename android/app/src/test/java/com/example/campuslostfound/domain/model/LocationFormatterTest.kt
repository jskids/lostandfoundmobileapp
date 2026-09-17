package com.example.campuslostfound.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class LocationFormatterTest {

    @Test
    fun `formatFull formats complete building floor and room correctly`() {
        val building = Building.MainAcademic
        val floor = Floor.FirstFloor
        val room = Room.ComputerLab1

        val result = LocationFormatter.formatFull(building, floor, room)

        assertEquals("MAB • 1F • Computer Lab 1 (101)", result)
    }

    @Test
    fun `formatShort formats complete location with short separator`() {
        val building = Building.ScienceComplex
        val floor = Floor.Basement
        val room = Room.StudyHall

        val result = LocationFormatter.formatShort(building, floor, room)

        assertEquals("SC - B1 - Study Hall", result)
    }

    @Test
    fun `formatFull handles missing building or floor gracefully`() {
        val floor = Floor.SecondFloor
        val room = Room.LibraryReadingRoom

        val result = LocationFormatter.formatFull(building = null, floor = floor, room = room)

        assertEquals("2F • Reading Room", result)
    }

    @Test
    fun `formatFull handles missing room gracefully`() {
        val building = Building.EngineeringHall
        val floor = Floor.ThirdFloor

        val result = LocationFormatter.formatFull(building, floor, room = null)

        assertEquals("EH • 3F", result)
    }

    @Test
    fun `formatFull returns unspecified location when all fields are null`() {
        val result = LocationFormatter.formatFull(building = null, floor = null, room = null)

        assertEquals("Unspecified Location", result)
    }

    @Test
    fun `formatFromIds resolves building floor and custom room correctly`() {
        val result = LocationFormatter.formatFromIds(
            buildingId = "central_library",
            floorId = "floor_2",
            roomInputOrId = "Study Nook 2B"
        )

        assertEquals("CL - 2F - Study Nook 2B", result)
    }

    @Test
    fun `formatFromIds handles null IDs gracefully`() {
        val result = LocationFormatter.formatFromIds(
            buildingId = null,
            floorId = null,
            roomInputOrId = null
        )

        assertEquals("Unspecified Location", result)
    }
}
