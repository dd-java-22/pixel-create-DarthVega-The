-- User table: Stores user account information, authentication details, and personalized preferences
CREATE TABLE user
(
    user_id                INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    username               TEXT                              NOT NULL,
    email                  TEXT                              NOT NULL,
    auth_token             TEXT,
    default_canvas_width   INTEGER                           NOT NULL,
    default_canvas_height  INTEGER                           NOT NULL,
    theme_preference       TEXT,
    grid_visibility        INTEGER                           NOT NULL,
    zoom_sensitivity       REAL                              NOT NULL,
    created_at             INTEGER                           NOT NULL,
    last_login             INTEGER
);

CREATE UNIQUE INDEX index_user_username ON user (username);

CREATE UNIQUE INDEX index_user_email ON user (email);

-- Project table: Stores pixel art project metadata and canvas information
CREATE TABLE project
(
    project_id      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    user_id         INTEGER                           NOT NULL,
    project_name    TEXT                              NOT NULL,
    canvas_width    INTEGER                           NOT NULL,
    canvas_height   INTEGER                           NOT NULL,
    created_at      INTEGER                           NOT NULL,
    last_edited_at  INTEGER                           NOT NULL,
    thumbnail_image BLOB,
    is_deleted      INTEGER                           NOT NULL,
    tags            TEXT,
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
);

CREATE INDEX index_project_user_id ON project (user_id);

CREATE INDEX index_project_project_name ON project (project_name);

-- Layer table: Stores drawing layers within projects with visibility and stacking controls
CREATE TABLE layer
(
    layer_id     INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    project_id   INTEGER                           NOT NULL,
    layer_name   TEXT                              NOT NULL,
    layer_order  INTEGER                           NOT NULL,
    is_visible   INTEGER                           NOT NULL,
    is_locked    INTEGER                           NOT NULL,
    opacity      REAL                              NOT NULL,
    created_at   INTEGER                           NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE
);

CREATE INDEX index_layer_project_id ON layer (project_id);

CREATE INDEX index_layer_project_id_layer_order ON layer (project_id, layer_order);

-- Pixel table: Stores individual pixel data with coordinates and color values
CREATE TABLE pixel
(
    pixel_id       INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    layer_id       INTEGER                           NOT NULL,
    x_coordinate   INTEGER                           NOT NULL,
    y_coordinate   INTEGER                           NOT NULL,
    color_value    INTEGER                           NOT NULL,
    last_modified  INTEGER                           NOT NULL,
    FOREIGN KEY (layer_id) REFERENCES layer (layer_id) ON DELETE CASCADE
);

CREATE INDEX index_pixel_layer_id ON pixel (layer_id);

CREATE UNIQUE INDEX index_pixel_layer_id_x_coordinate_y_coordinate
    ON pixel (layer_id, x_coordinate, y_coordinate);

-- Palette table: Stores user-created color palettes
CREATE TABLE palette
(
    palette_id   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    user_id      INTEGER                           NOT NULL,
    palette_name TEXT                              NOT NULL,
    is_default   INTEGER                           NOT NULL,
    created_at   INTEGER                           NOT NULL,
    last_used    INTEGER,
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
);

CREATE INDEX index_palette_user_id ON palette (user_id);

CREATE INDEX index_palette_palette_name ON palette (palette_name);

-- PaletteColor table: Stores individual colors within palettes
CREATE TABLE palette_color
(
    palette_color_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    palette_id       INTEGER                           NOT NULL,
    color_value      INTEGER                           NOT NULL,
    color_order      INTEGER                           NOT NULL,
    color_name       TEXT,
    FOREIGN KEY (palette_id) REFERENCES palette (palette_id) ON DELETE CASCADE
);

CREATE INDEX index_palette_color_palette_id ON palette_color (palette_id);

CREATE UNIQUE INDEX index_palette_color_palette_id_color_order
    ON palette_color (palette_id, color_order);

-- AutosaveSnapshot table: Stores periodic backup snapshots of project state
CREATE TABLE autosave_snapshot
(
    autosave_snapshot_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    project_id           INTEGER                           NOT NULL,
    snapshot_data        BLOB                              NOT NULL,
    created_at           INTEGER                           NOT NULL,
    file_size            INTEGER                           NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE
);

CREATE INDEX index_autosave_snapshot_project_id ON autosave_snapshot (project_id);

CREATE INDEX index_autosave_snapshot_project_id_created_at
    ON autosave_snapshot (project_id, created_at);

-- ExportHistory table: Stores records of exported images
CREATE TABLE export_history
(
    export_history_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    project_id        INTEGER                           NOT NULL,
    file_name         TEXT                              NOT NULL,
    file_path         TEXT                              NOT NULL,
    file_format       TEXT                              NOT NULL,
    resolution        INTEGER                           NOT NULL,
    scale_factor      INTEGER                           NOT NULL,
    exported_at       INTEGER                           NOT NULL,
    file_size_bytes   INTEGER                           NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE
);

CREATE INDEX index_export_history_project_id ON export_history (project_id);

CREATE INDEX index_export_history_project_id_exported_at
    ON export_history (project_id, exported_at);
