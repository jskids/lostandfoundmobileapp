-- PostgreSQL Database Schema Creation Script
-- Project: Campus Lost & Found
-- Task 4.10: Create database relationships
-- Task 4.11: Create database indexes
-- Task 4.12: Create database constraints
--
-- This file defines the table creation schema for the 6 core entities of the application along with foreign key constraints, cascading rules, database indexes, and CHECK/UNIQUE constraints.
-- Data types, nullability, default values, and column names strictly align with the serialization names (@SerialName) in the Kotlin DTOs.
-- Foreign key constraints and cascade rules match logical relationship requirements.
-- Indexes optimize foreign key lookups, frequent status/type filtering, and timestamp sorting.
-- CHECK and UNIQUE constraints enforce domain integrity rules on enums, user roles, notification types, and duplicate records.

-- Enable uuid-ossp extension for UUID generation functions
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ==========================================
-- 1. PROFILES TABLE
-- Mapped from: UserProfileDto.kt
-- Table Name: profiles
-- ==========================================
CREATE TABLE IF NOT EXISTS profiles (
    id UUID PRIMARY KEY REFERENCES auth.users(id) ON DELETE CASCADE, -- Matches the Supabase Auth user ID (auth.users.id) with cascade delete
    full_name TEXT NOT NULL,
    email TEXT NOT NULL,
    student_id TEXT,
    phone_number TEXT,
    avatar_url TEXT,
    role TEXT NOT NULL DEFAULT 'student', -- Default role is 'student' ('student', 'admin', 'staff')
    is_anonymous_default BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT unique_profiles_email UNIQUE (email),
    CONSTRAINT unique_profiles_student_id UNIQUE (student_id),
    CONSTRAINT chk_profiles_role CHECK (role IN ('student', 'admin', 'staff'))
);

-- ==========================================
-- 2. CATEGORIES TABLE
-- Mapped from: CategoryDto.kt
-- Table Name: categories
-- ==========================================
CREATE TABLE IF NOT EXISTS categories (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT NOT NULL,
    icon_name TEXT,
    description TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT unique_categories_name UNIQUE (name)
);

-- ==========================================
-- 3. LOCATIONS TABLE
-- Mapped from: CampusLocationDto.kt
-- Table Name: locations
-- ==========================================
CREATE TABLE IF NOT EXISTS locations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    building TEXT NOT NULL,
    floor TEXT,
    room TEXT,
    description TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

-- ==========================================
-- 4. ITEMS TABLE
-- Mapped from: LostFoundItemDto.kt
-- Table Name: items
-- ==========================================
CREATE TABLE IF NOT EXISTS items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES profiles(id) ON DELETE CASCADE, -- Foreign key referencing profiles.id with cascade delete
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    type TEXT NOT NULL, -- "LOST" or "FOUND"
    category_id UUID REFERENCES categories(id) ON DELETE SET NULL, -- Foreign key referencing categories.id with SET NULL on deletion
    location_id UUID REFERENCES locations(id) ON DELETE SET NULL, -- Foreign key referencing locations.id with SET NULL on deletion
    building TEXT,
    floor TEXT,
    room TEXT,
    date_reported TIMESTAMP WITH TIME ZONE,
    image_url TEXT,
    is_anonymous BOOLEAN NOT NULL DEFAULT FALSE,
    submitted_to_do BOOLEAN NOT NULL DEFAULT FALSE, -- Tracks whether the physical item has been turned over to the Disciplinary Office
    status TEXT NOT NULL DEFAULT 'UNSETTLED', -- "UNSETTLED", "PENDING", or "CLAIMED"
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT chk_items_type CHECK (type IN ('LOST', 'FOUND')),
    CONSTRAINT chk_items_status CHECK (status IN ('UNSETTLED', 'PENDING', 'CLAIMED'))
);

-- ==========================================
-- 5. CLAIMS TABLE
-- Mapped from: ItemClaimDto.kt
-- Table Name: claims
-- ==========================================
CREATE TABLE IF NOT EXISTS claims (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    item_id UUID NOT NULL REFERENCES items(id) ON DELETE CASCADE, -- Foreign key referencing items.id with cascade delete
    claimer_id UUID NOT NULL REFERENCES profiles(id) ON DELETE CASCADE, -- Foreign key referencing profiles.id with cascade delete
    proof_description TEXT NOT NULL,
    proof_image_url TEXT,
    status TEXT NOT NULL DEFAULT 'PENDING', -- "PENDING", "APPROVED", "REJECTED", or "CANCELLED"
    admin_notes TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT chk_claims_status CHECK (status IN ('PENDING', 'APPROVED', 'REJECTED', 'CANCELLED')),
    CONSTRAINT unique_claims_item_claimer UNIQUE (item_id, claimer_id)
);

-- ==========================================
-- 6. NOTIFICATIONS TABLE
-- Mapped from: AppNotificationDto.kt
-- Table Name: notifications
-- ==========================================
CREATE TABLE IF NOT EXISTS notifications (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES profiles(id) ON DELETE CASCADE, -- Foreign key referencing profiles.id with cascade delete
    title TEXT NOT NULL,
    message TEXT NOT NULL,
    type TEXT NOT NULL DEFAULT 'SYSTEM', -- "NEW_ITEM", "CLAIM_UPDATE", "MENTION", "SYSTEM"
    related_item_id UUID REFERENCES items(id) ON DELETE CASCADE, -- Foreign key referencing items.id with cascade delete
    related_claim_id UUID REFERENCES claims(id) ON DELETE CASCADE, -- Foreign key referencing claims.id with cascade delete
    is_read BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT chk_notifications_type CHECK (type IN ('NEW_ITEM', 'CLAIM_UPDATE', 'MENTION', 'SYSTEM'))
);

-- ==========================================
-- 7. DATABASE INDEXES (Task 4.11)
-- ==========================================

-- Profiles Indexes
CREATE INDEX IF NOT EXISTS idx_profiles_email ON profiles(email);
CREATE INDEX IF NOT EXISTS idx_profiles_role ON profiles(role);

-- Items Indexes (Foreign Keys, Filtering & Sorting)
CREATE INDEX IF NOT EXISTS idx_items_user_id ON items(user_id);
CREATE INDEX IF NOT EXISTS idx_items_category_id ON items(category_id);
CREATE INDEX IF NOT EXISTS idx_items_location_id ON items(location_id);
CREATE INDEX IF NOT EXISTS idx_items_type ON items(type);
CREATE INDEX IF NOT EXISTS idx_items_status ON items(status);
CREATE INDEX IF NOT EXISTS idx_items_submitted_to_do ON items(submitted_to_do);
CREATE INDEX IF NOT EXISTS idx_items_created_at ON items(created_at DESC);
CREATE INDEX IF NOT EXISTS idx_items_type_status ON items(type, status);

-- Claims Indexes (Foreign Keys, Filtering & Sorting)
CREATE INDEX IF NOT EXISTS idx_claims_item_id ON claims(item_id);
CREATE INDEX IF NOT EXISTS idx_claims_claimer_id ON claims(claimer_id);
CREATE INDEX IF NOT EXISTS idx_claims_status ON claims(status);
CREATE INDEX IF NOT EXISTS idx_claims_created_at ON claims(created_at DESC);

-- Notifications Indexes (Foreign Keys, Filtering & Sorting)
CREATE INDEX IF NOT EXISTS idx_notifications_user_id ON notifications(user_id);
CREATE INDEX IF NOT EXISTS idx_notifications_related_item_id ON notifications(related_item_id);
CREATE INDEX IF NOT EXISTS idx_notifications_related_claim_id ON notifications(related_claim_id);
CREATE INDEX IF NOT EXISTS idx_notifications_is_read ON notifications(is_read);
CREATE INDEX IF NOT EXISTS idx_notifications_user_is_read ON notifications(user_id, is_read);
CREATE INDEX IF NOT EXISTS idx_notifications_created_at ON notifications(created_at DESC);

-- ==========================================
-- 8. DATABASE CONSTRAINTS (Task 4.12)
-- Safe idempotent DDL statements for applying constraints to pre-existing tables
-- ==========================================

DO $$
BEGIN
    -- Profiles role check constraint
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'chk_profiles_role') THEN
        ALTER TABLE profiles ADD CONSTRAINT chk_profiles_role CHECK (role IN ('student', 'admin', 'staff'));
    END IF;

    -- Profiles email unique constraint
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'unique_profiles_email') THEN
        ALTER TABLE profiles ADD CONSTRAINT unique_profiles_email UNIQUE (email);
    END IF;

    -- Profiles student_id unique constraint
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'unique_profiles_student_id') THEN
        ALTER TABLE profiles ADD CONSTRAINT unique_profiles_student_id UNIQUE (student_id);
    END IF;

    -- Categories name unique constraint
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'unique_categories_name') THEN
        ALTER TABLE categories ADD CONSTRAINT unique_categories_name UNIQUE (name);
    END IF;

    -- Items type check constraint
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'chk_items_type') THEN
        ALTER TABLE items ADD CONSTRAINT chk_items_type CHECK (type IN ('LOST', 'FOUND'));
    END IF;

    -- Items status check constraint
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'chk_items_status') THEN
        ALTER TABLE items ADD CONSTRAINT chk_items_status CHECK (status IN ('UNSETTLED', 'PENDING', 'CLAIMED'));
    END IF;

    -- Claims status check constraint
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'chk_claims_status') THEN
        ALTER TABLE claims ADD CONSTRAINT chk_claims_status CHECK (status IN ('PENDING', 'APPROVED', 'REJECTED', 'CANCELLED'));
    END IF;

    -- Claims item_id + claimer_id unique constraint to prevent duplicate claims per user per item
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'unique_claims_item_claimer') THEN
        ALTER TABLE claims ADD CONSTRAINT unique_claims_item_claimer UNIQUE (item_id, claimer_id);
    END IF;

    -- Notifications type check constraint
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'chk_notifications_type') THEN
        ALTER TABLE notifications ADD CONSTRAINT chk_notifications_type CHECK (type IN ('NEW_ITEM', 'CLAIM_UPDATE', 'MENTION', 'SYSTEM'));
    END IF;
END $$;
