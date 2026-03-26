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
package edu.cnm.deepdive.pixelcreate.service;

import android.content.Context;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import edu.cnm.deepdive.pixelcreate.model.dao.AutosaveSnapshotDao;
import edu.cnm.deepdive.pixelcreate.model.dao.ExportHistoryDao;
import edu.cnm.deepdive.pixelcreate.model.dao.LayerDao;
import edu.cnm.deepdive.pixelcreate.model.dao.PaletteColorDao;
import edu.cnm.deepdive.pixelcreate.model.dao.PaletteDao;
import edu.cnm.deepdive.pixelcreate.model.dao.PixelDao;
import edu.cnm.deepdive.pixelcreate.model.dao.ProjectDao;
import edu.cnm.deepdive.pixelcreate.model.dao.UserDao;
import javax.inject.Singleton;

/**
 * Provides Hilt dependency injection configuration for Room database and DAOs. This module
 * creates singleton instances of the database and all Data Access Objects (DAOs).
 */
@InstallIn(SingletonComponent.class)
@Module
public class DatabaseModule {

  @Provides
  @Singleton
  LocalDatabase provideLocalDatabase(@ApplicationContext Context context) {
    return Room
        .databaseBuilder(context, LocalDatabase.class, LocalDatabase.DATABASE_NAME)
        .build();
  }

  @Provides
  @Singleton
  UserDao provideUserDao(LocalDatabase database) {
    return database.getUserDao();
  }

  @Provides
  @Singleton
  ProjectDao provideProjectDao(LocalDatabase database) {
    return database.getProjectDao();
  }

  @Provides
  @Singleton
  LayerDao provideLayerDao(LocalDatabase database) {
    return database.getLayerDao();
  }

  @Provides
  @Singleton
  PixelDao providePixelDao(LocalDatabase database) {
    return database.getPixelDao();
  }

  @Provides
  @Singleton
  PaletteDao providePaletteDao(LocalDatabase database) {
    return database.getPaletteDao();
  }

  @Provides
  @Singleton
  PaletteColorDao providePaletteColorDao(LocalDatabase database) {
    return database.getPaletteColorDao();
  }

  @Provides
  @Singleton
  AutosaveSnapshotDao provideAutosaveSnapshotDao(LocalDatabase database) {
    return database.getAutosaveSnapshotDao();
  }

  @Provides
  @Singleton
  ExportHistoryDao provideExportHistoryDao(LocalDatabase database) {
    return database.getExportHistoryDao();
  }

}
