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
import edu.cnm.deepdive.pixelcreate.model.entity.Pixel;
import java.util.Collection;
import java.util.List;

/**
 * Data Access Object (DAO) interface for {@link Pixel} entity. Declares CRUD operations
 * for managing individual pixel data within layers.
 */
@Dao
public interface PixelDao {

  /**
   * Inserts a single pixel into the database.
   *
   * @param pixel Pixel instance to insert.
   * @return ID of the newly inserted pixel.
   */
  @Insert
  long insert(Pixel pixel);

  /**
   * Inserts multiple pixels into the database.
   *
   * @param pixels Collection of pixels to insert.
   * @return List of IDs of the newly inserted pixels.
   */
  @Insert
  List<Long> insert(Collection<Pixel> pixels);

  /**
   * Inserts multiple pixels into the database.
   *
   * @param pixels Variable number of pixels to insert.
   * @return List of IDs of the newly inserted pixels.
   */
  @Insert
  List<Long> insert(Pixel... pixels);

  /**
   * Updates an existing pixel in the database.
   *
   * @param pixel Pixel instance with updated values.
   * @return Number of rows updated.
   */
  @Update
  int update(Pixel pixel);

  /**
   * Updates multiple existing pixels in the database.
   *
   * @param pixels Variable number of pixels with updated values.
   * @return Number of rows updated.
   */
  @Update
  int update(Pixel... pixels);

  /**
   * Deletes a pixel from the database.
   *
   * @param pixel Pixel instance to delete.
   * @return Number of rows deleted.
   */
  @Delete
  int delete(Pixel pixel);

  /**
   * Deletes multiple pixels from the database.
   *
   * @param pixels Variable number of pixels to delete.
   * @return Number of rows deleted.
   */
  @Delete
  int delete(Pixel... pixels);

  /**
   * Retrieves a pixel by its ID.
   *
   * @param pixelId Pixel ID.
   * @return LiveData containing the pixel, or {@code null} if not found.
   */
  @Query("SELECT * FROM pixel WHERE pixel_id = :pixelId")
  LiveData<Pixel> select(long pixelId);

  /**
   * Retrieves all pixels for a specific layer.
   *
   * @param layerId Layer ID.
   * @return LiveData containing a list of pixels for the specified layer.
   */
  @Query("SELECT * FROM pixel WHERE layer_id = :layerId ORDER BY y_coordinate ASC, x_coordinate ASC")
  LiveData<List<Pixel>> selectByLayer(long layerId);

  /**
   * Retrieves a specific pixel at the given coordinates within a layer.
   *
   * @param layerId Layer ID.
   * @param x X coordinate.
   * @param y Y coordinate.
   * @return LiveData containing the pixel at the specified position, or {@code null} if not found.
   */
  @Query("SELECT * FROM pixel WHERE layer_id = :layerId AND x_coordinate = :x AND y_coordinate = :y")
  LiveData<Pixel> selectByCoordinates(long layerId, int x, int y);

  /**
   * Retrieves pixels within a specific rectangular region of a layer.
   *
   * @param layerId Layer ID.
   * @param minX Minimum X coordinate.
   * @param maxX Maximum X coordinate.
   * @param minY Minimum Y coordinate.
   * @param maxY Maximum Y coordinate.
   * @return LiveData containing a list of pixels within the specified region.
   */
  @Query("SELECT * FROM pixel WHERE layer_id = :layerId AND x_coordinate BETWEEN :minX AND :maxX AND y_coordinate BETWEEN :minY AND :maxY")
  LiveData<List<Pixel>> selectByRegion(long layerId, int minX, int maxX, int minY, int maxY);

  /**
   * Retrieves all pixels of a specific color within a layer.
   *
   * @param layerId Layer ID.
   * @param colorValue Color value to search for.
   * @return LiveData containing a list of pixels with the specified color.
   */
  @Query("SELECT * FROM pixel WHERE layer_id = :layerId AND color_value = :colorValue")
  LiveData<List<Pixel>> selectByColor(long layerId, int colorValue);

  /**
   * Gets the count of pixels in a specific layer.
   *
   * @param layerId Layer ID.
   * @return LiveData containing the number of pixels in the layer.
   */
  @Query("SELECT COUNT(*) FROM pixel WHERE layer_id = :layerId")
  LiveData<Integer> countByLayer(long layerId);

  /**
   * Updates the color value of a specific pixel.
   *
   * @param pixelId Pixel ID.
   * @param colorValue New color value.
   * @return Number of rows updated.
   */
  @Query("UPDATE pixel SET color_value = :colorValue, last_modified = CURRENT_TIMESTAMP WHERE pixel_id = :pixelId")
  int updateColor(long pixelId, int colorValue);

  /**
   * Updates or inserts a pixel at specific coordinates (upsert operation).
   * Deletes existing pixel and returns 0, or returns 0 if no pixel exists.
   *
   * @param layerId Layer ID.
   * @param x X coordinate.
   * @param y Y coordinate.
   * @return Number of rows deleted (for existing pixel).
   */
  @Query("DELETE FROM pixel WHERE layer_id = :layerId AND x_coordinate = :x AND y_coordinate = :y")
  int deleteByCoordinates(long layerId, int x, int y);

  /**
   * Deletes all pixels in a specific layer.
   *
   * @param layerId Layer ID.
   * @return Number of rows deleted.
   */
  @Query("DELETE FROM pixel WHERE layer_id = :layerId")
  int deleteByLayer(long layerId);

  /**
   * Deletes all pixels of a specific color within a layer.
   *
   * @param layerId Layer ID.
   * @param colorValue Color value to delete.
   * @return Number of rows deleted.
   */
  @Query("DELETE FROM pixel WHERE layer_id = :layerId AND color_value = :colorValue")
  int deleteByColor(long layerId, int colorValue);
}
