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
import edu.cnm.deepdive.pixelcreate.model.entity.PaletteColor;
import java.util.Collection;
import java.util.List;

/**
 * Data Access Object (DAO) interface for {@link PaletteColor} entity. Declares CRUD operations
 * for managing individual colors within palettes.
 */
@Dao
public interface PaletteColorDao {

  /**
   * Inserts a single palette color into the database.
   *
   * @param paletteColor PaletteColor instance to insert.
   * @return ID of the newly inserted palette color.
   */
  @Insert
  long insert(PaletteColor paletteColor);

  /**
   * Inserts multiple palette colors into the database.
   *
   * @param paletteColors Collection of palette colors to insert.
   * @return List of IDs of the newly inserted palette colors.
   */
  @Insert
  List<Long> insert(Collection<PaletteColor> paletteColors);

  /**
   * Inserts multiple palette colors into the database.
   *
   * @param paletteColors Variable number of palette colors to insert.
   * @return List of IDs of the newly inserted palette colors.
   */
  @Insert
  List<Long> insert(PaletteColor... paletteColors);

  /**
   * Updates an existing palette color in the database.
   *
   * @param paletteColor PaletteColor instance with updated values.
   * @return Number of rows updated.
   */
  @Update
  int update(PaletteColor paletteColor);

  /**
   * Updates multiple existing palette colors in the database.
   *
   * @param paletteColors Variable number of palette colors with updated values.
   * @return Number of rows updated.
   */
  @Update
  int update(PaletteColor... paletteColors);

  /**
   * Deletes a palette color from the database.
   *
   * @param paletteColor PaletteColor instance to delete.
   * @return Number of rows deleted.
   */
  @Delete
  int delete(PaletteColor paletteColor);

  /**
   * Deletes multiple palette colors from the database.
   *
   * @param paletteColors Variable number of palette colors to delete.
   * @return Number of rows deleted.
   */
  @Delete
  int delete(PaletteColor... paletteColors);

  /**
   * Retrieves a palette color by its ID.
   *
   * @param paletteColorId Palette color ID.
   * @return LiveData containing the palette color, or {@code null} if not found.
   */
  @Query("SELECT * FROM palette_color WHERE palette_color_id = :paletteColorId")
  LiveData<PaletteColor> select(long paletteColorId);

  /**
   * Retrieves all colors for a specific palette, ordered by color order.
   *
   * @param paletteId Palette ID.
   * @return LiveData containing a list of colors for the specified palette.
   */
  @Query("SELECT * FROM palette_color WHERE palette_id = :paletteId ORDER BY color_order ASC")
  LiveData<List<PaletteColor>> selectByPalette(long paletteId);

  /**
   * Retrieves a palette color by its position within the palette.
   *
   * @param paletteId Palette ID.
   * @param colorOrder Color order (position).
   * @return LiveData containing the palette color at the specified position, or {@code null} if not found.
   */
  @Query("SELECT * FROM palette_color WHERE palette_id = :paletteId AND color_order = :colorOrder")
  LiveData<PaletteColor> selectByOrder(long paletteId, int colorOrder);

  /**
   * Retrieves all colors with a specific color value within a palette.
   *
   * @param paletteId Palette ID.
   * @param colorValue Color value to search for.
   * @return LiveData containing a list of palette colors with the specified color value.
   */
  @Query("SELECT * FROM palette_color WHERE palette_id = :paletteId AND color_value = :colorValue")
  LiveData<List<PaletteColor>> selectByColorValue(long paletteId, int colorValue);

  /**
   * Gets the count of colors in a specific palette.
   *
   * @param paletteId Palette ID.
   * @return LiveData containing the number of colors in the palette.
   */
  @Query("SELECT COUNT(*) FROM palette_color WHERE palette_id = :paletteId")
  LiveData<Integer> countByPalette(long paletteId);

  /**
   * Updates the color order for a specific palette color.
   *
   * @param paletteColorId Palette color ID.
   * @param newOrder New color order value.
   * @return Number of rows updated.
   */
  @Query("UPDATE palette_color SET color_order = :newOrder WHERE palette_color_id = :paletteColorId")
  int updateColorOrder(long paletteColorId, int newOrder);

  /**
   * Updates the color value for a specific palette color.
   *
   * @param paletteColorId Palette color ID.
   * @param colorValue New color value.
   * @return Number of rows updated.
   */
  @Query("UPDATE palette_color SET color_value = :colorValue WHERE palette_color_id = :paletteColorId")
  int updateColorValue(long paletteColorId, int colorValue);

  /**
   * Deletes all colors in a specific palette.
   *
   * @param paletteId Palette ID.
   * @return Number of rows deleted.
   */
  @Query("DELETE FROM palette_color WHERE palette_id = :paletteId")
  int deleteByPalette(long paletteId);
}
