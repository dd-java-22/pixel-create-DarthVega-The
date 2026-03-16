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
import edu.cnm.deepdive.pixelcreate.model.entity.ExportHistory;
import java.util.Collection;
import java.util.List;

/**
 * Data Access Object (DAO) interface for {@link ExportHistory} entity. Declares CRUD operations
 * for managing export history records. Note: Export records are generally immutable once created,
 * so no update operations are provided.
 */
@Dao
public interface ExportHistoryDao {

  /**
   * Inserts a single export history record into the database.
   *
   * @param exportHistory ExportHistory instance to insert.
   * @return ID of the newly inserted export record.
   */
  @Insert
  long insert(ExportHistory exportHistory);

  /**
   * Inserts multiple export history records into the database.
   *
   * @param exportHistories Collection of export records to insert.
   * @return List of IDs of the newly inserted export records.
   */
  @Insert
  List<Long> insert(Collection<ExportHistory> exportHistories);

  /**
   * Inserts multiple export history records into the database.
   *
   * @param exportHistories Variable number of export records to insert.
   * @return List of IDs of the newly inserted export records.
   */
  @Insert
  List<Long> insert(ExportHistory... exportHistories);

  /**
   * Deletes an export history record from the database.
   *
   * @param exportHistory ExportHistory instance to delete.
   * @return Number of rows deleted.
   */
  @Delete
  int delete(ExportHistory exportHistory);

  /**
   * Deletes multiple export history records from the database.
   *
   * @param exportHistories Variable number of export records to delete.
   * @return Number of rows deleted.
   */
  @Delete
  int delete(ExportHistory... exportHistories);

  /**
   * Retrieves an export history record by its ID.
   *
   * @param exportHistoryId Export history ID.
   * @return LiveData containing the export record, or {@code null} if not found.
   */
  @Query("SELECT * FROM export_history WHERE export_history_id = :exportHistoryId")
  LiveData<ExportHistory> select(long exportHistoryId);

  /**
   * Retrieves all export history records for a specific project, ordered by export time (most recent first).
   *
   * @param projectId Project ID.
   * @return LiveData containing a list of export records for the specified project.
   */
  @Query("SELECT * FROM export_history WHERE project_id = :projectId ORDER BY exported_at DESC")
  LiveData<List<ExportHistory>> selectByProject(long projectId);

  /**
   * Retrieves the most recent export record for a specific project.
   *
   * @param projectId Project ID.
   * @return LiveData containing the most recent export record, or {@code null} if none exist.
   */
  @Query("SELECT * FROM export_history WHERE project_id = :projectId ORDER BY exported_at DESC LIMIT 1")
  LiveData<ExportHistory> selectMostRecent(long projectId);

  /**
   * Retrieves export history records for a specific project and file format.
   *
   * @param projectId Project ID.
   * @param fileFormat File format (e.g., "PNG", "JPG").
   * @return LiveData containing a list of export records with the specified format.
   */
  @Query("SELECT * FROM export_history WHERE project_id = :projectId AND file_format = :fileFormat ORDER BY exported_at DESC")
  LiveData<List<ExportHistory>> selectByFormat(long projectId, String fileFormat);

  /**
   * Retrieves export history records for a specific project and resolution.
   *
   * @param projectId Project ID.
   * @param resolution Resolution value.
   * @return LiveData containing a list of export records with the specified resolution.
   */
  @Query("SELECT * FROM export_history WHERE project_id = :projectId AND resolution = :resolution ORDER BY exported_at DESC")
  LiveData<List<ExportHistory>> selectByResolution(long projectId, int resolution);

  /**
   * Gets the count of export records for a specific project.
   *
   * @param projectId Project ID.
   * @return LiveData containing the number of export records for the project.
   */
  @Query("SELECT COUNT(*) FROM export_history WHERE project_id = :projectId")
  LiveData<Integer> countByProject(long projectId);

  /**
   * Gets the total storage size (in bytes) used by exports for a specific project.
   *
   * @param projectId Project ID.
   * @return LiveData containing the total file size in bytes.
   */
  @Query("SELECT SUM(file_size_bytes) FROM export_history WHERE project_id = :projectId")
  LiveData<Long> getTotalSizeByProject(long projectId);

  /**
   * Retrieves all export history records across all projects, ordered by export time (most recent first).
   *
   * @return LiveData containing a list of all export records.
   */
  @Query("SELECT * FROM export_history ORDER BY exported_at DESC")
  LiveData<List<ExportHistory>> selectAll();

  /**
   * Retrieves a limited number of recent export records across all projects.
   *
   * @param limit Maximum number of records to retrieve.
   * @return LiveData containing a list of recent export records.
   */
  @Query("SELECT * FROM export_history ORDER BY exported_at DESC LIMIT :limit")
  LiveData<List<ExportHistory>> selectRecent(int limit);

  /**
   * Deletes all export history records for a specific project.
   *
   * @param projectId Project ID.
   * @return Number of rows deleted.
   */
  @Query("DELETE FROM export_history WHERE project_id = :projectId")
  int deleteByProject(long projectId);

  /**
   * Deletes export history records older than a specific export record.
   *
   * @param projectId Project ID.
   * @param exportHistoryId Export history ID threshold (records older than this will be deleted).
   * @return Number of rows deleted.
   */
  @Query("DELETE FROM export_history WHERE project_id = :projectId AND export_history_id < :exportHistoryId")
  int deleteOlderThan(long projectId, long exportHistoryId);
}
