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
import edu.cnm.deepdive.pixelcreate.model.entity.User;
import java.util.concurrent.CompletableFuture;

/**
 * Repository interface for {@link User} entity operations. Provides methods for user
 * authentication, profile management, and user data access.
 */
public interface UserRepository {

  /**
   * Saves or updates a user in the database.
   *
   * @param user User entity to save.
   * @return CompletableFuture containing the saved User with generated ID.
   */
  CompletableFuture<User> save(User user);

  /**
   * Retrieves a user by their unique identifier.
   *
   * @param id User ID.
   * @return LiveData containing the User, or null if not found.
   */
  LiveData<User> getById(long id);

  /**
   * Retrieves a user by their OAuth key.
   *
   * @param oauthKey OAuth authentication key.
   * @return LiveData containing the User, or null if not found.
   */
  LiveData<User> getByOauthKey(String oauthKey);

  /**
   * Deletes a user from the database.
   *
   * @param user User entity to delete.
   * @return CompletableFuture that completes when deletion is finished.
   */
  CompletableFuture<Void> delete(User user);

}
