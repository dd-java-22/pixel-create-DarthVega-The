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
package edu.cnm.deepdive.pixelcreate.service.repository;

import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.pixelcreate.model.entity.Project;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Repository interface for {@link Project} entity operations. Provides methods for project
 * creation, retrieval, update, and deletion.
 */
public interface ProjectRepository {

  /**
   * Saves or updates a project in the database.
   *
   * @param project Project entity to save.
   * @return CompletableFuture containing the saved Project with generated ID.
   */
  CompletableFuture<Project> save(Project project);

  /**
   * Retrieves a project by its unique identifier.
   *
   * @param id Project ID.
   * @return LiveData containing the Project, or null if not found.
   */
  LiveData<Project> getById(long id);

  /**
   * Retrieves all projects for a specific user.
   *
   * @param userId User ID.
   * @return LiveData containing a list of Projects.
   */
  LiveData<List<Project>> getAllByUserId(long userId);

  /**
   * Retrieves all non-deleted projects for a specific user.
   *
   * @param userId User ID.
   * @return LiveData containing a list of Projects.
   */
  LiveData<List<Project>> getActiveByUserId(long userId);

  /**
   * Deletes a project from the database.
   *
   * @param project Project entity to delete.
   * @return CompletableFuture that completes when deletion is finished.
   */
  CompletableFuture<Void> delete(Project project);

}
