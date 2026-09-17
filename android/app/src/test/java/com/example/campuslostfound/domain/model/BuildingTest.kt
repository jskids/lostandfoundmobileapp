package com.example.campuslostfound.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit tests for [Building] domain model, verification of values registry,
 * building codes, and lookup helper functions.
 */
class BuildingTest {

    @Test
    fun `values list contains all registered campus buildings`() {
        val buildings = Building.values
        assertEquals(8, buildings.size)
        assertTrue(buildings.contains(Building.MainAcademic))
        assertTrue(buildings.contains(Building.ScienceComplex))
        assertTrue(buildings.contains(Building.CentralLibrary))
        assertTrue(buildings.contains(Building.StudentCenter))
        assertTrue(buildings.contains(Building.EngineeringHall))
        assertTrue(buildings.contains(Building.Gymnasium))
        assertTrue(buildings.contains(Building.Administration))
        assertTrue(buildings.contains(Building.Cafeteria))
    }

    @Test
    fun `all registered buildings have unique non-empty ids`() {
        val buildings = Building.values
        val ids = buildings.map { it.id }

        // Assert all IDs are unique
        assertEquals(buildings.size, ids.toSet().size)

        // Assert all IDs are non-blank
        buildings.forEach { building ->
            assertTrue("Building $building has an empty ID", building.id.isNotBlank())
        }
    }

    @Test
    fun `all registered buildings have unique non-empty codes`() {
        val buildings = Building.values
        val codes = buildings.map { it.code }

        // Assert all codes are unique
        assertEquals(buildings.size, codes.toSet().size)

        // Assert all codes are non-blank
        buildings.forEach { building ->
            assertTrue("Building $building has an empty code", building.code.isNotBlank())
        }
    }

    @Test
    fun `all registered buildings have valid name string resource IDs`() {
        Building.values.forEach { building ->
            assertTrue("Building ${building.id} has invalid nameResId", building.nameResId != 0)
        }
    }

    @Test
    fun `fromId correctly resolves buildings by persistent id case-insensitively`() {
        assertEquals(Building.MainAcademic, Building.fromId("main_academic"))
        assertEquals(Building.MainAcademic, Building.fromId("MAIN_ACADEMIC"))
        assertEquals(Building.ScienceComplex, Building.fromId("science_complex"))
        assertEquals(Building.CentralLibrary, Building.fromId("central_library"))
        assertEquals(Building.StudentCenter, Building.fromId("student_center"))
        assertEquals(Building.EngineeringHall, Building.fromId("engineering_hall"))
        assertEquals(Building.Gymnasium, Building.fromId("gymnasium"))
        assertEquals(Building.Administration, Building.fromId("administration"))
        assertEquals(Building.Cafeteria, Building.fromId("cafeteria"))
    }

    @Test
    fun `fromId returns null for invalid or empty building id`() {
        assertNull(Building.fromId("unknown_building"))
        assertNull(Building.fromId(""))
        assertNull(Building.fromId("  "))
    }

    @Test
    fun `fromCode correctly resolves buildings by code case-insensitively`() {
        assertEquals(Building.MainAcademic, Building.fromCode("mab"))
        assertEquals(Building.MainAcademic, Building.fromCode("MAB"))
        assertEquals(Building.ScienceComplex, Building.fromCode("SC"))
        assertEquals(Building.CentralLibrary, Building.fromCode("CL"))
        assertEquals(Building.StudentCenter, Building.fromCode("SUB"))
        assertEquals(Building.EngineeringHall, Building.fromCode("EH"))
        assertEquals(Building.Gymnasium, Building.fromCode("GYM"))
        assertEquals(Building.Administration, Building.fromCode("ADMIN"))
        assertEquals(Building.Cafeteria, Building.fromCode("CAF"))
    }

    @Test
    fun `fromCode returns null for invalid or empty building code`() {
        assertNull(Building.fromCode("XYZ"))
        assertNull(Building.fromCode(""))
        assertNull(Building.fromCode("  "))
    }
}
