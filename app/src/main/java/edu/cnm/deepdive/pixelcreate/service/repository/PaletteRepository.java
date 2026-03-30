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
import edu.cnm.deepdive.pixelcreate.model.entity.Palette;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Repository interface for {@link Palette} entity operations. Provides methods for palette
 * creation, retrieval, update, and deletion.
 */
public interface PaletteRepository {

  /**
   * Saves or updates a palette in the database.
   *
   * @param palette Palette entity to save.
   * @return CompletableFuture containing the saved Palette with generated ID.
   */
  CompletableFuture<Palette> save(Palette palette);

  /**
   * Retrieves a palette by its unique identifier.
   *
   * @param id Palette ID.
   * @return LiveData containing the Palette, or null if not found.
   */
  LiveData<Palette> getById(long id);

  /**
   * Retrieves all palettes for a specific user.
   *
   * @param userId User ID.
   * @return LiveData containing a list of Palettes.
   */
  LiveData<List<Palette>> getAllByUserId(long userId);

  /**
   * Deletes a palette from the database.
   *
   * @param palette Palette entity to delete.
   * @return CompletableFuture that completes when deletion is finished.
   */
  CompletableFuture<Void> delete(Palette palette);

}
