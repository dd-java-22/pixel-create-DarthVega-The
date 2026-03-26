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
import edu.cnm.deepdive.pixelcreate.model.dao.PaletteDao;
import edu.cnm.deepdive.pixelcreate.model.entity.Palette;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Implementation of {@link PaletteRepository} providing palette data operations. Uses Room
 * database for data persistence and CompletableFuture for asynchronous operations.
 */
@Singleton
public class PaletteRepositoryImpl implements PaletteRepository {

  private static final int THREAD_POOL_SIZE = 4;

  private final PaletteDao paletteDao;
  private final Executor executor;

  @Inject
  PaletteRepositoryImpl(PaletteDao paletteDao) {
    this.paletteDao = paletteDao;
    executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
  }

  @Override
  public CompletableFuture<Palette> save(Palette palette) {
    return CompletableFuture.supplyAsync(() -> {
      if (palette.getId() == 0) {
        palette.setId(paletteDao.insert(palette));
      } else {
        paletteDao.update(palette);
      }
      return palette;
    }, executor);
  }

  @Override
  public LiveData<Palette> getById(long id) {
    return paletteDao.select(id);
  }

  @Override
  public LiveData<List<Palette>> getAllByUserId(long userId) {
    return paletteDao.selectByUser(userId);
  }

  @Override
  public CompletableFuture<Void> delete(Palette palette) {
    return CompletableFuture.runAsync(() -> paletteDao.delete(palette), executor);
  }

}
