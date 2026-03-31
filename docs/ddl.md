---
title: SQL DDL
description: "SQL Data Definition Language statements for database schema"
order: 12
---

## Data Definition Language

The SQL DDL (Data Definition Language) statements below define the complete database schema for the Pixel Create application. These statements create all tables, primary keys, foreign keys, unique constraints, and indices as specified in the entity classes.

### Database Schema

## Table Descriptions

### user
Stores user account information including credentials, authentication tokens, and personalized settings such as default canvas dimensions and UI preferences.

### project
Central table for pixel art projects containing metadata, canvas dimensions, timestamps, and thumbnail previews for gallery display.

### layer
Manages drawing layers within projects with support for layer ordering (Z-index), visibility toggling, locking, and opacity controls.

### pixel
Stores individual pixel color data at specific coordinates within layers. Includes unique constraint ensuring one pixel per coordinate per layer.

### palette
User-created color collections with support for both default (system-provided) and custom palettes, tracked by usage frequency.

### palette_color
Individual colors within palettes, ordered by position with optional user-defined color names.

### autosave_snapshot
Periodic backups of project state stored as binary data for recovery and undo functionality.

### export_history
Records of all export operations including file details, format, resolution, and timestamps for tracking and re-export capabilities.

## Notes

- All timestamp fields (created_at, last_edited_at, exported_at, etc.) store values as INTEGER representing milliseconds since epoch (Unix timestamp).
- Boolean fields (is_deleted, is_visible, is_locked, is_default, grid_visibility) are stored as INTEGER (0 = false, 1 = true).
- All foreign key relationships include `ON DELETE CASCADE` to maintain referential integrity.
- Indices are created on foreign keys and frequently queried columns to optimize performance.

