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
import edu.cnm.deepdive.pixelcreate.model.entity.Layer;
import java.util.Collection;
import java.util.List;

/**
 * Data Access Object (DAO) interface for {@link Layer} entity. Declares CRUD operations
 * for managing drawing layers within projects.
 */
@Dao
public interface LayerDao {

  /**
   * Inserts a single layer into the database.
   *
   * @param layer Layer instance to insert.
   * @return ID of the newly inserted layer.
   */
  @Insert
  long insert(Layer layer);

  /**
   * Inserts multiple layers into the database.
   *
   * @param layers Collection of layers to insert.
   * @return List of IDs of the newly inserted layers.
   */
  @Insert
  List<Long> insert(Collection<Layer> layers);

  /**
   * Inserts multiple layers into the database.
   *
   * @param layers Variable number of layers to insert.
   * @return List of IDs of the newly inserted layers.
   */
  @Insert
  List<Long> insert(Layer... layers);

  /**
   * Updates an existing layer in the database.
   *
   * @param layer Layer instance with updated values.
   * @return Number of rows updated.
   */
  @Update
  int update(Layer layer);

  /**
   * Updates multiple existing layers in the database.
   *
   * @param layers Variable number of layers with updated values.
   * @return Number of rows updated.
   */
  @Update
  int update(Layer... layers);

  /**
   * Deletes a layer from the database. Note: Cascades to related pixels.
   *
   * @param layer Layer instance to delete.
   * @return Number of rows deleted.
   */
  @Delete
  int delete(Layer layer);

  /**
   * Deletes multiple layers from the database.
   *
   * @param layers Variable number of layers to delete.
   * @return Number of rows deleted.
   */
  @Delete
  int delete(Layer... layers);

  /**
   * Retrieves a layer by its ID.
   *
   * @param layerId Layer ID.
   * @return LiveData containing the layer, or {@code null} if not found.
   */
  @Query("SELECT * FROM layer WHERE layer_id = :layerId")
  LiveData<Layer> select(long layerId);

  /**
   * Retrieves all layers for a specific project, ordered by layer order (Z-index).
   *
   * @param projectId Project ID.
   * @return LiveData containing a list of layers for the specified project.
   */
  @Query("SELECT * FROM layer WHERE project_id = :projectId ORDER BY layer_order ASC")
  LiveData<List<Layer>> selectByProject(long projectId);

  /**
   * Retrieves all visible layers for a specific project, ordered by layer order.
   *
   * @param projectId Project ID.
   * @return LiveData containing a list of visible layers for the specified project.
   */
  @Query("SELECT * FROM layer WHERE project_id = :projectId AND is_visible = 1 ORDER BY layer_order ASC")
  LiveData<List<Layer>> selectVisibleByProject(long projectId);

  /**
   * Retrieves all unlocked layers for a specific project, ordered by layer order.
   *
   * @param projectId Project ID.
   * @return LiveData containing a list of unlocked layers for the specified project.
   */
  @Query("SELECT * FROM layer WHERE project_id = :projectId AND is_locked = 0 ORDER BY layer_order ASC")
  LiveData<List<Layer>> selectUnlockedByProject(long projectId);

  /**
   * Gets the count of layers in a specific project.
   *
   * @param projectId Project ID.
   * @return LiveData containing the number of layers in the project.
   */
  @Query("SELECT COUNT(*) FROM layer WHERE project_id = :projectId")
  LiveData<Integer> countByProject(long projectId);

  /**
   * Updates the layer order for a specific layer.
   *
   * @param layerId Layer ID.
   * @param newOrder New layer order value.
   * @return Number of rows updated.
   */
  @Query("UPDATE layer SET layer_order = :newOrder WHERE layer_id = :layerId")
  int updateLayerOrder(long layerId, int newOrder);

  /**
   * Toggles the visibility of a layer.
   *
   * @param layerId Layer ID.
   * @param isVisible New visibility state.
   * @return Number of rows updated.
   */
  @Query("UPDATE layer SET is_visible = :isVisible WHERE layer_id = :layerId")
  int updateVisibility(long layerId, boolean isVisible);

  /**
   * Toggles the locked state of a layer.
   *
   * @param layerId Layer ID.
   * @param isLocked New locked state.
   * @return Number of rows updated.
   */
  @Query("UPDATE layer SET is_locked = :isLocked WHERE layer_id = :layerId")
  int updateLockedState(long layerId, boolean isLocked);

  /**
   * Updates the opacity of a layer.
   *
   * @param layerId Layer ID.
   * @param opacity New opacity value (0.0 to 1.0).
   * @return Number of rows updated.
   */
  @Query("UPDATE layer SET opacity = :opacity WHERE layer_id = :layerId")
  int updateOpacity(long layerId, float opacity);
}
