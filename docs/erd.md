---
title: Entity-Relationship Diagram
description: "Database schema and entity relationships for Pixel Create"
order: 11
---

## Entity-Relationship Diagram

This ERD represents the database structure for the Pixel Create pixel art application, designed to support local persistence using Room Database with potential for cloud synchronization.

[![updated-erd-diagram](img/updated-erd-diagram.svg)](pdf/updated-erd-diagram.pdf)

[//]: # (```mermaid)

[//]: # (erDiagram)

[//]: # (    User ||--o{ Project : creates)

[//]: # (    User ||--o{ Palette : creates)

[//]: # (    Project ||--|{ Layer : contains)

[//]: # (    Layer ||--o{ Pixel : contains)

[//]: # (    Project ||--o{ AutosaveSnapshot : has)

[//]: # (    Project ||--o{ ExportHistory : generates)

[//]: # (    Palette ||--|{ PaletteColor : contains)

[//]: # ()
[//]: # (    User {)

[//]: # (        int userId PK)

[//]: # (        string username)

[//]: # (        string email)

[//]: # (        string authToken)

[//]: # (        int defaultCanvasWidth)

[//]: # (        int defaultCanvasHeight)

[//]: # (        string themePreference)

[//]: # (        boolean gridVisibility)

[//]: # (        float zoomSensitivity)

[//]: # (        timestamp createdAt)

[//]: # (        timestamp lastLogin)

[//]: # (    })

[//]: # ()
[//]: # (    Project {)

[//]: # (        int projectId PK)

[//]: # (        int userId FK)

[//]: # (        string projectName)

[//]: # (        int canvasWidth)

[//]: # (        int canvasHeight)

[//]: # (        timestamp createdAt)

[//]: # (        timestamp lastEditedAt)

[//]: # (        blob thumbnailImage)

[//]: # (        boolean isDeleted)

[//]: # (        string tags)

[//]: # (    })

[//]: # ()
[//]: # (    Layer {)

[//]: # (        int layerId PK)

[//]: # (        int projectId FK)

[//]: # (        string layerName)

[//]: # (        int layerOrder)

[//]: # (        boolean isVisible)

[//]: # (        boolean isLocked)

[//]: # (        float opacity)

[//]: # (        timestamp createdAt)

[//]: # (    })

[//]: # ()
[//]: # (    Pixel {)

[//]: # (        int pixelId PK)

[//]: # (        int layerId FK)

[//]: # (        int xCoordinate)

[//]: # (        int yCoordinate)

[//]: # (        int colorValue)

[//]: # (        timestamp lastModified)

[//]: # (    })

[//]: # ()
[//]: # (    Palette {)

[//]: # (        int paletteId PK)

[//]: # (        int userId FK)

[//]: # (        string paletteName)

[//]: # (        boolean isDefault)

[//]: # (        timestamp createdAt)

[//]: # (        timestamp lastUsed)

[//]: # (    })

[//]: # ()
[//]: # (    PaletteColor {)

[//]: # (        int colorId PK)

[//]: # (        int paletteId FK)

[//]: # (        int colorValue)

[//]: # (        int colorOrder)

[//]: # (        string colorName)

[//]: # (    })

[//]: # ()
[//]: # (    AutosaveSnapshot {)

[//]: # (        int snapshotId PK)

[//]: # (        int projectId FK)

[//]: # (        blob snapshotData)

[//]: # (        timestamp createdAt)

[//]: # (        int fileSize)

[//]: # (    })

[//]: # ()
[//]: # (    ExportHistory {)

[//]: # (        int exportId PK)

[//]: # (        int projectId FK)

[//]: # (        string fileName)

[//]: # (        string filePath)

[//]: # (        string fileFormat)

[//]: # (        int resolution)

[//]: # (        int scaleFactor)

[//]: # (        timestamp exportedAt)

[//]: # (        int fileSizeBytes)

[//]: # (    })

[//]: # (```)

## Entity Descriptions

### Core Entities

#### User
Stores user account information, authentication details, and personalized settings that persist across devices.

**Key Attributes:**
- `userId`: Primary key, unique identifier
- `username`, `email`: Account credentials
- `defaultCanvasWidth/Height`: User preference for new projects
- `themePreference`: Light/dark mode setting
- `gridVisibility`, `zoomSensitivity`: Tool preferences

**Relationships:**
- One user creates many projects (1:N)
- One user creates many custom palettes (1:N)

---

#### Project
The central entity representing a pixel art creation. Contains metadata about canvas size, timestamps, and thumbnail preview.

**Key Attributes:**
- `projectId`: Primary key
- `projectName`: User-defined name
- `canvasWidth/Height`: Pixel dimensions of the canvas
- `thumbnailImage`: Small preview for gallery display
- `isDeleted`: Soft-delete flag for recovery

**Relationships:**
- One project has many layers (1:N, mandatory - at least one layer)
- One project has many autosave snapshots (1:N)
- One project generates many export history records (1:N)

---

#### Layer
Represents individual drawing layers within a project, supporting complex artwork with stacking and visibility controls.

**Key Attributes:**
- `layerId`: Primary key
- `layerOrder`: Stacking position (Z-index)
- `isVisible`: Show/hide toggle
- `isLocked`: Prevent editing
- `opacity`: Transparency level (0.0 - 1.0)

**Relationships:**
- One layer contains many pixels (1:N)
- Multiple layers belong to one project (N:1)

---

#### Pixel
Stores individual pixel color data. Each pixel is tied to a specific layer and position.

**Key Attributes:**
- `pixelId`: Primary key
- `xCoordinate`, `yCoordinate`: Position on the canvas
- `colorValue`: ARGB or hex color value
- `lastModified`: Timestamp for tracking changes

**Relationships:**
- Many pixels belong to one layer (N:1)

**Design Note:**
For large canvases, storing each pixel individually may require optimization (e.g., sparse storage for transparent pixels, compression, or delta encoding).

---

### Color Management

#### Palette
User-created color collections for consistent artwork styling.

**Key Attributes:**
- `paletteId`: Primary key
- `paletteName`: User-defined name
- `isDefault`: Flag for system-provided palettes
- `lastUsed`: For sorting recently used palettes

**Relationships:**
- One palette contains many colors (1:N, mandatory - at least one color)
- One user creates many palettes (N:1)

---

#### PaletteColor
Individual colors within a palette.

**Key Attributes:**
- `colorId`: Primary key
- `colorValue`: ARGB or hex representation
- `colorOrder`: Display order within palette
- `colorName`: Optional user-defined label (e.g., "Sky Blue")

**Relationships:**
- Many colors belong to one palette (N:1)

---

### Data Persistence & History

#### AutosaveSnapshot
Periodic backups of project state to prevent data loss.

**Key Attributes:**
- `snapshotId`: Primary key
- `snapshotData`: Serialized project state (could be JSON or binary)
- `fileSize`: Storage tracking

**Relationships:**
- Many snapshots belong to one project (N:1)

**Design Note:**
Consider retention policy (e.g., keep last 10 snapshots, delete older than 30 days).

---

#### ExportHistory
Logs every export operation for tracking and re-export functionality.

**Key Attributes:**
- `exportId`: Primary key
- `fileName`: Generated or user-specified name
- `filePath`: Location in device storage
- `fileFormat`: PNG, JPG, etc.
- `resolution`, `scaleFactor`: Export dimensions

**Relationships:**
- Many exports belong to one project (N:1)

---

## Relationship Cardinalities

| Relationship | Cardinality | Description |
|-------------|-------------|-------------|
| User → Project | 1:N | One user creates many projects |
| User → Palette | 1:N | One user creates many palettes |
| Project → Layer | 1:N | One project has many layers (minimum 1) |
| Layer → Pixel | 1:N | One layer contains many pixels |
| Project → AutosaveSnapshot | 1:N | One project has many snapshots |
| Project → ExportHistory | 1:N | One project has many export records |
| Palette → PaletteColor | 1:N | One palette contains many colors (minimum 1) |

---

## Database Schema Location

The Room database schema is stored in:
```
app/schemas/edu.cnm.deepdive.myproject.service.LocalDatabase/1.json
```

DDL output location (configured in `build.gradle.kts`):
```
docs/sql/ddl.sql
```

---

## Design Considerations

### Normalization
The schema follows 3NF (Third Normal Form):
- No transitive dependencies
- Each entity has a single-column primary key
- Relationship tables (junction tables) would be added if many-to-many relationships are needed (e.g., shared palettes)

### Indexing Recommendations
- Index `projectId` in Layer, AutosaveSnapshot, ExportHistory
- Index `layerId` in Pixel
- Index `paletteId` in PaletteColor
- Composite index on `(xCoordinate, yCoordinate, layerId)` for pixel lookups
- Index `userId` in Project and Palette

### Performance Optimizations
- **Pixel Storage**: For large canvases, consider using sparse storage or run-length encoding to reduce storage for transparent/empty pixels
- **Autosave Strategy**: Implement differential snapshots rather than full project serialization
- **Thumbnail Generation**: Generate thumbnails asynchronously and cache in-memory
- **Lazy Loading**: Load pixel data only for visible layers in the viewport

### Future Extensions (Stretch Goals)
If animation features are added:
- **AnimationFrame** entity (linked to Project)
- **FramePixel** entity (linking frames to pixel data with timing information)

If collaboration features are added:
- **ProjectCollaborator** junction table (User-Project many-to-many)
- **VersionHistory** entity for tracking changes and merges

---

## Entity Source Code

The following entity classes implement this ERD schema in Java using Room Database annotations. Click each link to view the source code on GitHub:

### Core Entities
- [**User.java**](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/entity/User.java) - User account and preferences
- [**Project.java**](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/entity/Project.java) - Pixel art project metadata
- [**Layer.java**](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/entity/Layer.java) - Drawing layer within a project
- [**Pixel.java**](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/entity/Pixel.java) - Individual pixel data

### Color Management
- [**Palette.java**](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/entity/Palette.java) - Color palette collection
- [**PaletteColor.java**](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/entity/PaletteColor.java) - Individual palette color

### Data Persistence & History
- [**AutosaveSnapshot.java**](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/entity/AutosaveSnapshot.java) - Project backup snapshot
- [**ExportHistory.java**](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/entity/ExportHistory.java) - Export operation record

For detailed descriptions and a complete overview of all entity classes, see the [Entity Classes](entities.md) page.

## DAO Interface Source Code

Each entity has a corresponding Data Access Object (DAO) interface that provides CRUD operations and custom queries. Click each link to view the source code on GitHub:

### Core Entity DAOs
- [**UserDao.java**](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/dao/UserDao.java) - User data access operations
- [**ProjectDao.java**](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/dao/ProjectDao.java) - Project data access operations
- [**LayerDao.java**](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/dao/LayerDao.java) - Layer data access operations
- [**PixelDao.java**](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/dao/PixelDao.java) - Pixel data access operations

### Color Management DAOs
- [**PaletteDao.java**](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/dao/PaletteDao.java) - Palette data access operations
- [**PaletteColorDao.java**](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/dao/PaletteColorDao.java) - Palette color data access operations

### Data Persistence & History DAOs
- [**AutosaveSnapshotDao.java**](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/dao/AutosaveSnapshotDao.java) - Autosave snapshot data access operations
- [**ExportHistoryDao.java**](https://github.com/dd-java-22/pixel-create-DarthVega-The/blob/main/app/src/main/java/edu/cnm/deepdive/pixelcreate/model/dao/ExportHistoryDao.java) - Export history data access operations

---


