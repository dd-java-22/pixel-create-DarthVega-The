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
package edu.cnm.deepdive.pixelcreate.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.pixelcreate.model.entity.Palette;
import java.util.Collection;
import java.util.List;

/**
 * Data Access Object (DAO) interface for {@link Palette} entity. Declares CRUD operations
 * for managing color palettes.
 */
@Dao
public interface PaletteDao {

  /**
   * Inserts a single palette into the database.
   *
   * @param palette Palette instance to insert.
   * @return ID of the newly inserted palette.
   */
  @Insert
  long insert(Palette palette);

  /**
   * Inserts multiple palettes into the database.
   *
   * @param palettes Collection of palettes to insert.
   * @return List of IDs of the newly inserted palettes.
   */
  @Insert
  List<Long> insert(Collection<Palette> palettes);

  /**
   * Inserts multiple palettes into the database.
   *
   * @param palettes Variable number of palettes to insert.
   * @return List of IDs of the newly inserted palettes.
   */
  @Insert
  List<Long> insert(Palette... palettes);

  /**
   * Updates an existing palette in the database.
   *
   * @param palette Palette instance with updated values.
   * @return Number of rows updated.
   */
  @Update
  int update(Palette palette);

  /**
   * Updates multiple existing palettes in the database.
   *
   * @param palettes Variable number of palettes with updated values.
   * @return Number of rows updated.
   */
  @Update
  int update(Palette... palettes);

  /**
   * Deletes a palette from the database. Note: Cascades to related palette colors.
   *
   * @param palette Palette instance to delete.
   * @return Number of rows deleted.
   */
  @Delete
  int delete(Palette palette);

  /**
   * Deletes multiple palettes from the database.
   *
   * @param palettes Variable number of palettes to delete.
   * @return Number of rows deleted.
   */
  @Delete
  int delete(Palette... palettes);

  /**
   * Retrieves a palette by its ID.
   *
   * @param paletteId Palette ID.
   * @return LiveData containing the palette, or {@code null} if not found.
   */
  @Query("SELECT * FROM palette WHERE palette_id = :paletteId")
  LiveData<Palette> select(long paletteId);

  /**
   * Retrieves all palettes for a specific user, ordered by last used (most recent first).
   *
   * @param userId User ID.
   * @return LiveData containing a list of palettes for the specified user.
   */
  @Query("SELECT * FROM palette WHERE user_id = :userId ORDER BY last_used DESC")
  LiveData<List<Palette>> selectByUser(long userId);

  /**
   * Retrieves all palettes for a specific user, ordered by palette name.
   *
   * @param userId User ID.
   * @return LiveData containing a list of palettes for the specified user.
   */
  @Query("SELECT * FROM palette WHERE user_id = :userId ORDER BY palette_name ASC")
  LiveData<List<Palette>> selectByUserOrderedByName(long userId);

  /**
   * Retrieves all default (system-provided) palettes.
   *
   * @return LiveData containing a list of default palettes.
   */
  @Query("SELECT * FROM palette WHERE is_default = 1 ORDER BY palette_name ASC")
  LiveData<List<Palette>> selectDefaultPalettes();

  /**
   * Retrieves all custom (user-created) palettes for a specific user.
   *
   * @param userId User ID.
   * @return LiveData containing a list of custom palettes for the specified user.
   */
  @Query("SELECT * FROM palette WHERE user_id = :userId AND is_default = 0 ORDER BY palette_name ASC")
  LiveData<List<Palette>> selectCustomByUser(long userId);

  /**
   * Retrieves a palette by name for a specific user.
   *
   * @param userId User ID.
   * @param paletteName Palette name to search for.
   * @return LiveData containing the palette, or {@code null} if not found.
   */
  @Query("SELECT * FROM palette WHERE user_id = :userId AND palette_name = :paletteName")
  LiveData<Palette> selectByName(long userId, String paletteName);

  /**
   * Updates the last used timestamp for a palette.
   *
   * @param paletteId Palette ID.
   * @return Number of rows updated.
   */
  @Query("UPDATE palette SET last_used = CURRENT_TIMESTAMP WHERE palette_id = :paletteId")
  int updateLastUsed(long paletteId);

  /**
   * Gets the count of palettes for a specific user.
   *
   * @param userId User ID.
   * @return LiveData containing the number of palettes for the user.
   */
  @Query("SELECT COUNT(*) FROM palette WHERE user_id = :userId")
  LiveData<Integer> countByUser(long userId);
}
