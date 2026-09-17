package com.example.campuslostfound.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit tests for [Floor] domain model, verification of values registry,
 * level numbers, floor codes, and lookup helper functions.
 */
class FloorTest {

    @Test
    fun `values list contains all registered campus floors ordered by level`() {
        val floors = Floor.values
        assertEquals(7, floors.size)
        assertEquals(Floor.Basement, floors[0])
        assertEquals(Floor.FirstFloor, floors[1])
        assertEquals(Floor.SecondFloor, floors[2])
        assertEquals(Floor.ThirdFloor, floors[3])
        assertEquals(Floor.FourthFloor, floors[4])
        assertEquals(Floor.FifthFloor, floors[5])
        assertEquals(Floor.RoofDeck, floors[6])
    }

    @Test
    fun `all registered floors have unique non-empty ids`() {
        val floors = Floor.values
        val ids = floors.map { it.id }

        // Assert all IDs are unique
        assertEquals(floors.size, ids.toSet().size)

        // Assert all IDs are non-blank
        floors.forEach { floor ->
            assertTrue("Floor $floor has an empty ID", floor.id.isNotBlank())
        }
    }

    @Test
    fun `all registered floors have unique numeric levels`() {
        val floors = Floor.values
        val levels = floors.map { it.level }

        // Assert all numeric levels are unique
        assertEquals(floors.size, levels.toSet().size)
    }

    @Test
    fun `all registered floors have unique non-empty codes`() {
        val floors = Floor.values
        val codes = floors.map { it.code }

        // Assert all codes are unique
        assertEquals(floors.size, codes.toSet().size)

        // Assert all codes are non-blank
        floors.forEach { floor ->
            assertTrue("Floor $floor has an empty code", floor.code.isNotBlank())
        }
    }

    @Test
    fun `all registered floors have valid name string resource IDs`() {
        Floor.values.forEach { floor ->
            assertTrue("Floor ${floor.id} has invalid nameResId", floor.nameResId != 0)
        }
    }

    @Test
    fun `fromId correctly resolves floors by persistent id case-insensitively`() {
        assertEquals(Floor.Basement, Floor.fromId("basement"))
        assertEquals(Floor.Basement, Floor.fromId("BASEMENT"))
        assertEquals(Floor.FirstFloor, Floor.fromId("floor_1"))
        assertEquals(Floor.SecondFloor, Floor.fromId("floor_2"))
        assertEquals(Floor.ThirdFloor, Floor.fromId("floor_3"))
        assertEquals(Floor.FourthFloor, Floor.fromId("floor_4"))
        assertEquals(Floor.FifthFloor, Floor.fromId("floor_5"))
        assertEquals(Floor.RoofDeck, Floor.fromId("roof_deck"))
    }

    @Test
    fun `fromId returns null for invalid or empty floor id`() {
        assertNull(Floor.fromId("unknown_floor"))
        assertNull(Floor.fromId(""))
        assertNull(Floor.fromId("  "))
    }

    @Test
    fun `fromLevel correctly resolves floors by numeric level`() {
        assertEquals(Floor.Basement, Floor.fromLevel(-1))
        assertEquals(Floor.FirstFloor, Floor.fromLevel(1))
        assertEquals(Floor.SecondFloor, Floor.fromLevel(2))
        assertEquals(Floor.ThirdFloor, Floor.fromLevel(3))
        assertEquals(Floor.FourthFloor, Floor.fromLevel(4))
        assertEquals(Floor.FifthFloor, Floor.fromLevel(5))
        assertEquals(Floor.RoofDeck, Floor.fromLevel(6))
    }

    @Test
    fun `fromLevel returns null for unregistered floor level`() {
        assertNull(Floor.fromLevel(0))
        assertNull(Floor.fromLevel(99))
        assertNull(Floor.fromLevel(-99))
    }

    @Test
    fun `fromCode correctly resolves floors by display code case-insensitively`() {
        assertEquals(Floor.Basement, Floor.fromCode("b1"))
        assertEquals(Floor.Basement, Floor.fromCode("B1"))
        assertEquals(Floor.FirstFloor, Floor.fromCode("1F"))
        assertEquals(Floor.SecondFloor, Floor.fromCode("2F"))
        assertEquals(Floor.ThirdFloor, Floor.fromCode("3F"))
        assertEquals(Floor.FourthFloor, Floor.fromCode("4F"))
        assertEquals(Floor.FifthFloor, Floor.fromCode("5F"))
        assertEquals(Floor.RoofDeck, Floor.fromCode("rf"))
    }

    @Test
    fun `fromCode returns null for invalid or empty floor code`() {
        assertNull(Floor.fromCode("10F"))
        assertNull(Floor.fromCode(""))
        assertNull(Floor.fromCode("  "))
    }
}
