---
title: Entity Classes
description: "Room database entity classes with links to source code"
order: 13
---

## Entity Classes

The Pixel Create application uses Room Database for local data persistence. The entity classes below represent the database schema and are mapped to SQLite tables. Each entity corresponds to a specific aspect of the pixel art creation workflow.

For a visual representation of how these entities relate to each other, see the [Entity-Relationship Diagram](erd.md) and [UML Class Diagram](uml.md).

### Core Entities

| Entity Class | Description | Source Code |
|--------------|-------------|-------------|
| **User** | Stores user account information, OAuth authentication key (unique identifier from authentication provider), and personalized settings including default canvas dimensions, theme preferences, and tool settings. | [User.java](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/entity/User.java) |
| **Project** | Represents a pixel art creation with metadata including project name, canvas dimensions, timestamps, thumbnail preview, and soft-delete flag. Central entity that ties together layers, snapshots, and export history. | [Project.java](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/entity/Project.java) |
| **Layer** | Individual drawing layers within a project supporting complex artwork with stacking order, visibility controls, lock state, and opacity settings. | [Layer.java](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/entity/Layer.java) |
| **Pixel** | Stores individual pixel color data tied to a specific layer and position. Contains x/y coordinates, color value, and last modified timestamp. | [Pixel.java](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/entity/Pixel.java) |

### Color Management Entities

| Entity Class | Description | Source Code |
|--------------|-------------|-------------|
| **Palette** | User-created color collections for consistent artwork styling. Contains palette name, default flag, and usage timestamps for sorting recently used palettes. | [Palette.java](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/entity/Palette.java) |
| **PaletteColor** | Individual colors within a palette. Stores color value (ARGB/hex), display order, and optional user-defined color name. | [PaletteColor.java](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/entity/PaletteColor.java) |

### Data Persistence & History Entities

| Entity Class | Description | Source Code |
|--------------|-------------|-------------|
| **AutosaveSnapshot** | Periodic backups of project state to prevent data loss. Contains serialized project data (JSON or binary), creation timestamp, and file size for storage tracking. | [AutosaveSnapshot.java](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/entity/AutosaveSnapshot.java) |
| **ExportHistory** | Logs every export operation for tracking and re-export functionality. Records file name, path, format (PNG, JPG, etc.), resolution, scale factor, and file size. | [ExportHistory.java](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/entity/ExportHistory.java) |

---

## Entity Relationships

The relationships between these entities follow standard database normalization principles:

- **User** → **Project**: One-to-many (users create multiple projects)
- **User** → **Palette**: One-to-many (users create multiple custom palettes)
- **Project** → **Layer**: One-to-many (projects contain multiple layers)
- **Project** → **AutosaveSnapshot**: One-to-many (projects have multiple snapshots)
- **Project** → **ExportHistory**: One-to-many (projects generate multiple exports)
- **Layer** → **Pixel**: One-to-many (layers contain multiple pixels)
- **Palette** → **PaletteColor**: One-to-many (palettes contain multiple colors)

For detailed cardinality information and relationship constraints, see the [ERD documentation](erd.md).

---

## Data Access Object (DAO) Interfaces

Each entity has a corresponding DAO interface that provides database operations (CRUD - Create, Read, Update, Delete). These interfaces are implemented by Room Database at compile time.

### Core Entity DAOs

| DAO Interface | Description | Source Code |
|---------------|-------------|-------------|
| **UserDao** | Data access operations for User entity. Provides methods to insert, update, delete users, and query by ID, username, or email. Returns LiveData for reactive UI updates. | [UserDao.java](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/dao/UserDao.java) |
| **ProjectDao** | Data access operations for Project entity. Manages project CRUD operations, queries projects by user, and supports filtering by deletion status. | [ProjectDao.java](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/dao/ProjectDao.java) |
| **LayerDao** | Data access operations for Layer entity. Handles layer management within projects, including ordering, visibility, and lock state queries. | [LayerDao.java](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/dao/LayerDao.java) |
| **PixelDao** | Data access operations for Pixel entity. Manages individual pixel data with queries by layer, coordinate, and bulk operations for efficient canvas rendering. | [PixelDao.java](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/dao/PixelDao.java) |

### Color Management DAOs

| DAO Interface | Description | Source Code |
|---------------|-------------|-------------|
| **PaletteDao** | Data access operations for Palette entity. Manages custom palette collections, queries by user, and tracks recently used palettes. | [PaletteDao.java](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/dao/PaletteDao.java) |
| **PaletteColorDao** | Data access operations for PaletteColor entity. Handles individual colors within palettes, including ordering and color lookup operations. | [PaletteColorDao.java](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/dao/PaletteColorDao.java) |

### Data Persistence & History DAOs

| DAO Interface | Description | Source Code |
|---------------|-------------|-------------|
| **AutosaveSnapshotDao** | Data access operations for AutosaveSnapshot entity. Manages project backup snapshots with retention policy support and timestamp-based queries. | [AutosaveSnapshotDao.java](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/dao/AutosaveSnapshotDao.java) |
| **ExportHistoryDao** | Data access operations for ExportHistory entity. Tracks export operations with queries by project, format, and date range for export analytics. | [ExportHistoryDao.java](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/dao/ExportHistoryDao.java) |

---

## Database Implementation

These entities and DAOs are managed by the Room Database framework. For more information:

- **Database Schema**: See [SQL DDL](ddl.md) for complete table definitions
- **Database Class**: The [`LocalDatabase`](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/service/LocalDatabase.java) class coordinates all DAOs and entities
- **Type Converters**: The `LocalDatabase.Converters` class handles conversion of `Instant` timestamps to SQLite-compatible types

---

## Related Documentation

- [Entity-Relationship Diagram](erd.md) - Visual representation of entity relationships
- [UML Class Diagram](uml.md) - Detailed class structure with methods and properties
- [SQL DDL](ddl.md) - Database schema definition language
- [Javadocs](api/) - Complete API documentation
