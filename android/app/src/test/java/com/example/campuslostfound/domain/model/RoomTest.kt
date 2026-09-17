package com.example.campuslostfound.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit tests for [Room] domain model, verification of preset rooms,
 * formatted location helper functions, and custom input creation.
 */
class RoomTest {

    @Test
    fun `presets list contains all registered campus room presets`() {
        val presets = Room.presets
        assertEquals(9, presets.size)
        assertTrue(presets.contains(Room.MainLobby))
        assertTrue(presets.contains(Room.StudyHall))
        assertTrue(presets.contains(Room.ComputerLab1))
        assertTrue(presets.contains(Room.ComputerLab2))
        assertTrue(presets.contains(Room.LibraryReadingRoom))
        assertTrue(presets.contains(Room.DeansOffice))
        assertTrue(presets.contains(Room.StudentLounge))
        assertTrue(presets.contains(Room.FoodCourt))
        assertTrue(presets.contains(Room.GymnasiumCourt))
    }

    @Test
    fun `all preset rooms have unique non-empty ids`() {
        val presets = Room.presets
        val ids = presets.map { it.id }

        assertEquals(presets.size, ids.toSet().size)

        presets.forEach { room ->
            assertTrue("Room $room has an empty ID", room.id.isNotBlank())
        }
    }

    @Test
    fun `displayTitle correctly formats room name and number`() {
        val roomWithNumber = Room(id = "r1", name = "Computer Lab 1", number = "101")
        assertEquals("Computer Lab 1 (101)", roomWithNumber.displayTitle)

        val roomNumberInName = Room(id = "r2", name = "Room 101", number = "101")
        assertEquals("Room 101", roomNumberInName.displayTitle)

        val roomNameOnly = Room(id = "r3", name = "Main Lobby")
        assertEquals("Main Lobby", roomNameOnly.displayTitle)

        val roomNumberOnly = Room(id = "r4", name = "", number = "302")
        assertEquals("Room 302", roomNumberOnly.displayTitle)
    }

    @Test
    fun `getFormattedLocation constructs full hierarchical location string`() {
        val room = Room(id = "r1", name = "Computer Lab 1", number = "101")
        val building = Building.MainAcademic
        val floor = Floor.FirstFloor

        val formattedWithAll = room.getFormattedLocation(building, floor)
        assertEquals("MAB - 1F - Computer Lab 1 (101)", formattedWithAll)

        val formattedWithBuildingOnly = room.getFormattedLocation(building = building)
        assertEquals("MAB - Computer Lab 1 (101)", formattedWithBuildingOnly)

        val formattedWithFloorOnly = room.getFormattedLocation(floor = floor)
        assertEquals("1F - Computer Lab 1 (101)", formattedWithFloorOnly)

        val formattedRoomOnly = room.getFormattedLocation()
        assertEquals("Computer Lab 1 (101)", formattedRoomOnly)
    }

    @Test
    fun `fromId correctly resolves preset rooms by persistent id case-insensitively`() {
        assertEquals(Room.MainLobby, Room.fromId("main_lobby"))
        assertEquals(Room.MainLobby, Room.fromId("MAIN_LOBBY"))
        assertEquals(Room.ComputerLab1, Room.fromId("comp_lab_1"))
        assertEquals(Room.DeansOffice, Room.fromId("deans_office"))
    }

    @Test
    fun `fromId returns null for unregistered room id`() {
        assertNull(Room.fromId("unknown_room"))
        assertNull(Room.fromId(""))
    }

    @Test
    fun `fromCustomInput creates custom Room instance with sanitized id and detected number`() {
        val customRoom1 = Room.fromCustomInput("204", buildingId = "main_academic", floorId = "floor_2")
        assertEquals("room_204", customRoom1.id)
        assertEquals("204", customRoom1.name)
        assertEquals("204", customRoom1.number)
        assertEquals("main_academic", customRoom1.buildingId)
        assertEquals("floor_2", customRoom1.floorId)

        val customRoom2 = Room.fromCustomInput("Conference Room B")
        assertEquals("room_conference_room_b", customRoom2.id)
        assertEquals("Conference Room B", customRoom2.name)
        assertNull(customRoom2.number)
    }
}
