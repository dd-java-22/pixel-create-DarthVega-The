/*
 *  Copyright 2026 CNM Ingenuity, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package edu.cnm.deepdive.pixelcreate.service;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import edu.cnm.deepdive.pixelcreate.model.dao.AutosaveSnapshotDao;
import edu.cnm.deepdive.pixelcreate.model.dao.ExportHistoryDao;
import edu.cnm.deepdive.pixelcreate.model.dao.LayerDao;
import edu.cnm.deepdive.pixelcreate.model.dao.PaletteColorDao;
import edu.cnm.deepdive.pixelcreate.model.dao.PaletteDao;
import edu.cnm.deepdive.pixelcreate.model.dao.PixelDao;
import edu.cnm.deepdive.pixelcreate.model.dao.ProjectDao;
import edu.cnm.deepdive.pixelcreate.model.dao.UserDao;
import edu.cnm.deepdive.pixelcreate.model.entity.AutosaveSnapshot;
import edu.cnm.deepdive.pixelcreate.model.entity.ExportHistory;
import edu.cnm.deepdive.pixelcreate.model.entity.Layer;
import edu.cnm.deepdive.pixelcreate.model.entity.Palette;
import edu.cnm.deepdive.pixelcreate.model.entity.PaletteColor;
import edu.cnm.deepdive.pixelcreate.model.entity.Pixel;
import edu.cnm.deepdive.pixelcreate.model.entity.Project;
import edu.cnm.deepdive.pixelcreate.model.entity.User;
import edu.cnm.deepdive.pixelcreate.service.LocalDatabase.Converters;
import java.time.Instant;

/**
 * Room database for the Pixel Create application. Provides access to all DAOs and manages
 * the local SQLite database containing user data, projects, layers, pixels, palettes, and
 * related entities.
 */
@Database(
    entities = {
        User.class,
        Project.class,
        Layer.class,
        Pixel.class,
        Palette.class,
        PaletteColor.class,
        AutosaveSnapshot.class,
        ExportHistory.class
    },
    version = 1
)
@TypeConverters(Converters.class)
public abstract class LocalDatabase extends RoomDatabase {

  static final String DATABASE_NAME = "pixel_create_db";

  static final int DATABASE_VERSION = 1;

  /**
   * Gets the DAO for {@link User} entity operations.
   *
   * @return UserDao instance.
   */
  public abstract UserDao getUserDao();

  /**
   * Gets the DAO for {@link Project} entity operations.
   *
   * @return ProjectDao instance.
   */
  public abstract ProjectDao getProjectDao();

  /**
   * Gets the DAO for {@link Layer} entity operations.
   *
   * @return LayerDao instance.
   */
  public abstract LayerDao getLayerDao();

  /**
   * Gets the DAO for {@link Pixel} entity operations.
   *
   * @return PixelDao instance.
   */
  public abstract PixelDao getPixelDao();

  /**
   * Gets the DAO for {@link Palette} entity operations.
   *
   * @return PaletteDao instance.
   */
  public abstract PaletteDao getPaletteDao();

  /**
   * Gets the DAO for {@link PaletteColor} entity operations.
   *
   * @return PaletteColorDao instance.
   */
  public abstract PaletteColorDao getPaletteColorDao();

  /**
   * Gets the DAO for {@link AutosaveSnapshot} entity operations.
   *
   * @return AutosaveSnapshotDao instance.
   */
  public abstract AutosaveSnapshotDao getAutosaveSnapshotDao();

  /**
   * Gets the DAO for {@link ExportHistory} entity operations.
   *
   * @return ExportHistoryDao instance.
   */
  public abstract ExportHistoryDao getExportHistoryDao();

  /**
   * Type converters for Room database. Provides conversion between Java types not natively
   * supported by SQLite (such as {@link Instant}) and SQLite-compatible types.
   */
  public static class Converters {

    /**
     * Converts a {@link Long} timestamp to an {@link Instant}.
     *
     * @param milliseconds Timestamp in milliseconds since epoch, or {@code null}.
     * @return Instant representation of the timestamp, or {@code null} if input is {@code null}.
     */
    @TypeConverter
    public static Instant longToInstant(Long milliseconds) {
      return (milliseconds != null) ? Instant.ofEpochMilli(milliseconds) : null;
    }

    /**
     * Converts an {@link Instant} to a {@link Long} timestamp.
     *
     * @param instant Instant to convert, or {@code null}.
     * @return Timestamp in milliseconds since epoch, or {@code null} if input is {@code null}.
     */
    @TypeConverter
    public static Long instantToLong(Instant instant) {
      return (instant != null) ? instant.toEpochMilli() : null;
    }
  }
}
