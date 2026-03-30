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
import edu.cnm.deepdive.pixelcreate.model.dao.UserDao;
import edu.cnm.deepdive.pixelcreate.model.entity.User;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Implementation of {@link UserRepository} providing user data operations. Uses Room database
 * for data persistence and CompletableFuture for asynchronous operations.
 */
@Singleton
public class UserRepositoryImpl implements UserRepository {

  private static final int THREAD_POOL_SIZE = 4;

  private final UserDao userDao;
  private final Executor executor;

  @Inject
  UserRepositoryImpl(UserDao userDao) {
    this.userDao = userDao;
    executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
  }

  @Override
  public CompletableFuture<User> save(User user) {
    return CompletableFuture.supplyAsync(() -> {
      if (user.getId() == 0) {
        user.setId(userDao.insert(user));
      } else {
        userDao.update(user);
      }
      return user;
    }, executor);
  }

  @Override
  public LiveData<User> getById(long id) {
    return userDao.select(id);
  }

  @Override
  public LiveData<User> getByOauthKey(String oauthKey) {
    return userDao.selectByOauthKey(oauthKey);
  }

  @Override
  public CompletableFuture<Void> delete(User user) {
    return CompletableFuture.runAsync(() -> userDao.delete(user), executor);
  }

}
