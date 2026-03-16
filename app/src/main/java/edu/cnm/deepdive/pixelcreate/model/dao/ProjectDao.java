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
import edu.cnm.deepdive.pixelcreate.model.entity.Project;
import java.util.Collection;
import java.util.List;

/**
 * Data Access Object (DAO) interface for {@link Project} entity. Declares CRUD operations
 * for managing pixel art projects.
 */
@Dao
public interface ProjectDao {

  /**
   * Inserts a single project into the database.
   *
   * @param project Project instance to insert.
   * @return ID of the newly inserted project.
   */
  @Insert
  long insert(Project project);

  /**
   * Inserts multiple projects into the database.
   *
   * @param projects Collection of projects to insert.
   * @return List of IDs of the newly inserted projects.
   */
  @Insert
  List<Long> insert(Collection<Project> projects);

  /**
   * Inserts multiple projects into the database.
   *
   * @param projects Variable number of projects to insert.
   * @return List of IDs of the newly inserted projects.
   */
  @Insert
  List<Long> insert(Project... projects);

  /**
   * Updates an existing project in the database.
   *
   * @param project Project instance with updated values.
   * @return Number of rows updated.
   */
  @Update
  int update(Project project);

  /**
   * Updates multiple existing projects in the database.
   *
   * @param projects Variable number of projects with updated values.
   * @return Number of rows updated.
   */
  @Update
  int update(Project... projects);

  /**
   * Deletes a project from the database. Note: Cascades to related layers, pixels,
   * autosave snapshots, and export history.
   *
   * @param project Project instance to delete.
   * @return Number of rows deleted.
   */
  @Delete
  int delete(Project project);

  /**
   * Deletes multiple projects from the database.
   *
   * @param projects Variable number of projects to delete.
   * @return Number of rows deleted.
   */
  @Delete
  int delete(Project... projects);

  /**
   * Retrieves a project by its ID.
   *
   * @param projectId Project ID.
   * @return LiveData containing the project, or {@code null} if not found.
   */
  @Query("SELECT * FROM project WHERE project_id = :projectId")
  LiveData<Project> select(long projectId);

  /**
   * Retrieves all projects for a specific user, ordered by last edited timestamp (most recent first).
   *
   * @param userId User ID.
   * @return LiveData containing a list of projects for the specified user.
   */
  @Query("SELECT * FROM project WHERE user_id = :userId AND is_deleted = 0 ORDER BY last_edited_at DESC")
  LiveData<List<Project>> selectByUser(long userId);

  /**
   * Retrieves all non-deleted projects for a specific user, ordered by project name.
   *
   * @param userId User ID.
   * @return LiveData containing a list of non-deleted projects for the specified user.
   */
  @Query("SELECT * FROM project WHERE user_id = :userId AND is_deleted = 0 ORDER BY project_name ASC")
  LiveData<List<Project>> selectByUserOrderedByName(long userId);

  /**
   * Retrieves all deleted projects for a specific user (for recovery functionality).
   *
   * @param userId User ID.
   * @return LiveData containing a list of deleted projects for the specified user.
   */
  @Query("SELECT * FROM project WHERE user_id = :userId AND is_deleted = 1 ORDER BY last_edited_at DESC")
  LiveData<List<Project>> selectDeletedByUser(long userId);

  /**
   * Retrieves all projects (including deleted) for a specific user.
   *
   * @param userId User ID.
   * @return LiveData containing a list of all projects for the specified user.
   */
  @Query("SELECT * FROM project WHERE user_id = :userId ORDER BY last_edited_at DESC")
  LiveData<List<Project>> selectAllByUser(long userId);

  /**
   * Retrieves projects by name search (case-insensitive partial match).
   *
   * @param userId User ID.
   * @param searchTerm Search term to match against project names.
   * @return LiveData containing a list of matching projects.
   */
  @Query("SELECT * FROM project WHERE user_id = :userId AND is_deleted = 0 AND project_name LIKE '%' || :searchTerm || '%' ORDER BY project_name ASC")
  LiveData<List<Project>> searchByName(long userId, String searchTerm);

  /**
   * Marks a project as deleted (soft delete).
   *
   * @param projectId Project ID to mark as deleted.
   * @return Number of rows updated.
   */
  @Query("UPDATE project SET is_deleted = 1 WHERE project_id = :projectId")
  int markAsDeleted(long projectId);

  /**
   * Restores a previously deleted project.
   *
   * @param projectId Project ID to restore.
   * @return Number of rows updated.
   */
  @Query("UPDATE project SET is_deleted = 0 WHERE project_id = :projectId")
  int restore(long projectId);

  /**
   * Permanently deletes all projects marked as deleted for a specific user.
   *
   * @param userId User ID.
   * @return Number of rows deleted.
   */
  @Query("DELETE FROM project WHERE user_id = :userId AND is_deleted = 1")
  int purgeDeleted(long userId);
}
