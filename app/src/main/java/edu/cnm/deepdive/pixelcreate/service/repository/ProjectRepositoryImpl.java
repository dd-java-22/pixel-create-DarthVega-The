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
import edu.cnm.deepdive.pixelcreate.model.dao.ProjectDao;
import edu.cnm.deepdive.pixelcreate.model.entity.Project;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Implementation of {@link ProjectRepository} providing project data operations. Uses Room
 * database for data persistence and CompletableFuture for asynchronous operations.
 */
@Singleton
public class ProjectRepositoryImpl implements ProjectRepository {

  private static final int THREAD_POOL_SIZE = 4;

  private final ProjectDao projectDao;
  private final Executor executor;

  @Inject
  ProjectRepositoryImpl(ProjectDao projectDao) {
    this.projectDao = projectDao;
    executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
  }

  @Override
  public CompletableFuture<Project> save(Project project) {
    return CompletableFuture.supplyAsync(() -> {
      if (project.getId() == 0) {
        project.setId(projectDao.insert(project));
      } else {
        projectDao.update(project);
      }
      return project;
    }, executor);
  }

  @Override
  public LiveData<Project> getById(long id) {
    return projectDao.select(id);
  }

  @Override
  public LiveData<List<Project>> getAllByUserId(long userId) {
    return projectDao.selectAllByUser(userId);
  }

  @Override
  public LiveData<List<Project>> getActiveByUserId(long userId) {
    return projectDao.selectByUser(userId);
  }

  @Override
  public CompletableFuture<Void> delete(Project project) {
    return CompletableFuture.runAsync(() -> projectDao.delete(project), executor);
  }

}
