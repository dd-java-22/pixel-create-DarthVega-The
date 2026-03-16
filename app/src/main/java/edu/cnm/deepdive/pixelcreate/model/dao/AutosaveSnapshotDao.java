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
import edu.cnm.deepdive.pixelcreate.model.entity.AutosaveSnapshot;
import java.util.Collection;
import java.util.List;

/**
 * Data Access Object (DAO) interface for {@link AutosaveSnapshot} entity. Declares CRUD operations
 * for managing autosave snapshots. Note: Snapshots are generally immutable once created, so no
 * update operations are provided.
 */
@Dao
public interface AutosaveSnapshotDao {

  /**
   * Inserts a single autosave snapshot into the database.
   *
   * @param snapshot AutosaveSnapshot instance to insert.
   * @return ID of the newly inserted snapshot.
   */
  @Insert
  long insert(AutosaveSnapshot snapshot);

  /**
   * Inserts multiple autosave snapshots into the database.
   *
   * @param snapshots Collection of snapshots to insert.
   * @return List of IDs of the newly inserted snapshots.
   */
  @Insert
  List<Long> insert(Collection<AutosaveSnapshot> snapshots);

  /**
   * Inserts multiple autosave snapshots into the database.
   *
   * @param snapshots Variable number of snapshots to insert.
   * @return List of IDs of the newly inserted snapshots.
   */
  @Insert
  List<Long> insert(AutosaveSnapshot... snapshots);

  /**
   * Deletes an autosave snapshot from the database.
   *
   * @param snapshot AutosaveSnapshot instance to delete.
   * @return Number of rows deleted.
   */
  @Delete
  int delete(AutosaveSnapshot snapshot);

  /**
   * Deletes multiple autosave snapshots from the database.
   *
   * @param snapshots Variable number of snapshots to delete.
   * @return Number of rows deleted.
   */
  @Delete
  int delete(AutosaveSnapshot... snapshots);

  /**
   * Retrieves an autosave snapshot by its ID.
   *
   * @param snapshotId Snapshot ID.
   * @return LiveData containing the snapshot, or {@code null} if not found.
   */
  @Query("SELECT * FROM autosave_snapshot WHERE autosave_snapshot_id = :snapshotId")
  LiveData<AutosaveSnapshot> select(long snapshotId);

  /**
   * Retrieves all autosave snapshots for a specific project, ordered by creation time (most recent first).
   *
   * @param projectId Project ID.
   * @return LiveData containing a list of snapshots for the specified project.
   */
  @Query("SELECT * FROM autosave_snapshot WHERE project_id = :projectId ORDER BY created_at DESC")
  LiveData<List<AutosaveSnapshot>> selectByProject(long projectId);

  /**
   * Retrieves the most recent autosave snapshot for a specific project.
   *
   * @param projectId Project ID.
   * @return LiveData containing the most recent snapshot, or {@code null} if none exist.
   */
  @Query("SELECT * FROM autosave_snapshot WHERE project_id = :projectId ORDER BY created_at DESC LIMIT 1")
  LiveData<AutosaveSnapshot> selectMostRecent(long projectId);

  /**
   * Retrieves a limited number of recent autosave snapshots for a specific project.
   *
   * @param projectId Project ID.
   * @param limit Maximum number of snapshots to retrieve.
   * @return LiveData containing a list of recent snapshots.
   */
  @Query("SELECT * FROM autosave_snapshot WHERE project_id = :projectId ORDER BY created_at DESC LIMIT :limit")
  LiveData<List<AutosaveSnapshot>> selectRecentByProject(long projectId, int limit);

  /**
   * Gets the count of autosave snapshots for a specific project.
   *
   * @param projectId Project ID.
   * @return LiveData containing the number of snapshots for the project.
   */
  @Query("SELECT COUNT(*) FROM autosave_snapshot WHERE project_id = :projectId")
  LiveData<Integer> countByProject(long projectId);

  /**
   * Gets the total storage size (in bytes) used by autosave snapshots for a specific project.
   *
   * @param projectId Project ID.
   * @return LiveData containing the total file size in bytes.
   */
  @Query("SELECT SUM(file_size) FROM autosave_snapshot WHERE project_id = :projectId")
  LiveData<Long> getTotalSizeByProject(long projectId);

  /**
   * Deletes all autosave snapshots for a specific project.
   *
   * @param projectId Project ID.
   * @return Number of rows deleted.
   */
  @Query("DELETE FROM autosave_snapshot WHERE project_id = :projectId")
  int deleteByProject(long projectId);

  /**
   * Deletes old autosave snapshots for a project, keeping only the most recent N snapshots.
   *
   * @param projectId Project ID.
   * @param keepCount Number of recent snapshots to keep.
   * @return Number of rows deleted.
   */
  @Query("DELETE FROM autosave_snapshot WHERE project_id = :projectId AND autosave_snapshot_id NOT IN (SELECT autosave_snapshot_id FROM autosave_snapshot WHERE project_id = :projectId ORDER BY created_at DESC LIMIT :keepCount)")
  int deleteOldSnapshots(long projectId, int keepCount);

  /**
   * Deletes autosave snapshots older than a specific snapshot ID for a project.
   *
   * @param projectId Project ID.
   * @param snapshotId Snapshot ID threshold (snapshots older than this will be deleted).
   * @return Number of rows deleted.
   */
  @Query("DELETE FROM autosave_snapshot WHERE project_id = :projectId AND autosave_snapshot_id < :snapshotId")
  int deleteOlderThan(long projectId, long snapshotId);
}
