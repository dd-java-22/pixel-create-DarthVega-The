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
import edu.cnm.deepdive.pixelcreate.model.entity.User;
import java.util.Collection;
import java.util.List;

/**
 * Data Access Object (DAO) interface for {@link User} entity. Declares CRUD operations
 * for managing user accounts and preferences.
 */
@Dao
public interface UserDao {

  /**
   * Inserts a single user into the database.
   *
   * @param user User instance to insert.
   * @return ID of the newly inserted user.
   */
  @Insert
  long insert(User user);

  /**
   * Inserts multiple users into the database.
   *
   * @param users Collection of users to insert.
   * @return List of IDs of the newly inserted users.
   */
  @Insert
  List<Long> insert(Collection<User> users);

  /**
   * Inserts multiple users into the database.
   *
   * @param users Variable number of users to insert.
   * @return List of IDs of the newly inserted users.
   */
  @Insert
  List<Long> insert(User... users);

  /**
   * Updates an existing user in the database.
   *
   * @param user User instance with updated values.
   * @return Number of rows updated.
   */
  @Update
  int update(User user);

  /**
   * Updates multiple existing users in the database.
   *
   * @param users Variable number of users with updated values.
   * @return Number of rows updated.
   */
  @Update
  int update(User... users);

  /**
   * Deletes a user from the database.
   *
   * @param user User instance to delete.
   * @return Number of rows deleted.
   */
  @Delete
  int delete(User user);

  /**
   * Deletes multiple users from the database.
   *
   * @param users Variable number of users to delete.
   * @return Number of rows deleted.
   */
  @Delete
  int delete(User... users);

  /**
   * Retrieves a user by their ID.
   *
   * @param userId User ID.
   * @return LiveData containing the user, or {@code null} if not found.
   */
  @Query("SELECT * FROM user WHERE user_id = :userId")
  LiveData<User> select(long userId);

  /**
   * Retrieves a user by their username.
   *
   * @param username Username to search for.
   * @return LiveData containing the user, or {@code null} if not found.
   */
  @Query("SELECT * FROM user WHERE username = :username")
  LiveData<User> selectByUsername(String username);

  /**
   * Retrieves a user by their email address.
   *
   * @param email Email address to search for.
   * @return LiveData containing the user, or {@code null} if not found.
   */
  @Query("SELECT * FROM user WHERE email = :email")
  LiveData<User> selectByEmail(String email);

  /**
   * Retrieves all users from the database, ordered by username.
   *
   * @return LiveData containing a list of all users.
   */
  @Query("SELECT * FROM user ORDER BY username ASC")
  LiveData<List<User>> selectAll();
}
