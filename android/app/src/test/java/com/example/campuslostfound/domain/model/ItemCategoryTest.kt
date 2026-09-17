package com.example.campuslostfound.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit tests for [ItemCategory] domain model, verification of values registry,
 * and persistent ID mapping functionality.
 */
class ItemCategoryTest {

    @Test
    fun `values list contains all 11 registered categories`() {
        val categories = ItemCategory.values
        assertEquals(11, categories.size)
        assertTrue(categories.contains(ItemCategory.Shoes))
        assertTrue(categories.contains(ItemCategory.Blazer))
        assertTrue(categories.contains(ItemCategory.Phone))
        assertTrue(categories.contains(ItemCategory.Wallet))
        assertTrue(categories.contains(ItemCategory.SchoolID))
        assertTrue(categories.contains(ItemCategory.Jacket))
        assertTrue(categories.contains(ItemCategory.Uniform))
        assertTrue(categories.contains(ItemCategory.Tumbler))
        assertTrue(categories.contains(ItemCategory.MiniFan))
        assertTrue(categories.contains(ItemCategory.Umbrella))
        assertTrue(categories.contains(ItemCategory.Others))
    }

    @Test
    fun `all registered categories have unique non-empty ids`() {
        val categories = ItemCategory.values
        val ids = categories.map { it.id }
        
        // Assert all IDs are unique
        assertEquals(categories.size, ids.toSet().size)

        // Assert all IDs are non-empty
        categories.forEach { category ->
            assertTrue("Category $category has an empty ID", category.id.isNotBlank())
        }
    }

    @Test
    fun `all registered categories have valid string and drawable resource IDs`() {
        ItemCategory.values.forEach { category ->
            assertTrue("Category ${category.id} has invalid displayNameResId", category.displayNameResId != 0)
            assertNotNull("Category ${category.id} has null iconResId", category.iconResId)
            assertTrue("Category ${category.id} has invalid iconResId", category.iconResId != 0)
        }
    }

    @Test
    fun `fromId correctly resolves categories by id case-insensitively`() {
        assertEquals(ItemCategory.Shoes, ItemCategory.fromId("shoes"))
        assertEquals(ItemCategory.Shoes, ItemCategory.fromId("SHOES"))
        assertEquals(ItemCategory.Blazer, ItemCategory.fromId("blazer"))
        assertEquals(ItemCategory.Phone, ItemCategory.fromId("phone"))
        assertEquals(ItemCategory.Wallet, ItemCategory.fromId("wallet"))
        assertEquals(ItemCategory.SchoolID, ItemCategory.fromId("school_id"))
        assertEquals(ItemCategory.Jacket, ItemCategory.fromId("jacket"))
        assertEquals(ItemCategory.Uniform, ItemCategory.fromId("uniform"))
        assertEquals(ItemCategory.Tumbler, ItemCategory.fromId("tumbler"))
        assertEquals(ItemCategory.MiniFan, ItemCategory.fromId("mini_fan"))
        assertEquals(ItemCategory.Umbrella, ItemCategory.fromId("umbrella"))
        assertEquals(ItemCategory.Others, ItemCategory.fromId("others"))
    }

    @Test
    fun `fromId returns null for invalid or non-existent category id`() {
        assertNull(ItemCategory.fromId("invalid_category_id"))
        assertNull(ItemCategory.fromId(""))
        assertNull(ItemCategory.fromId("  "))
    }
}
