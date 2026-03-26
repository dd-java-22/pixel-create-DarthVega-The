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

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import edu.cnm.deepdive.pixelcreate.service.repository.PaletteRepository;
import edu.cnm.deepdive.pixelcreate.service.repository.PaletteRepositoryImpl;
import edu.cnm.deepdive.pixelcreate.service.repository.ProjectRepository;
import edu.cnm.deepdive.pixelcreate.service.repository.ProjectRepositoryImpl;
import edu.cnm.deepdive.pixelcreate.service.repository.UserRepository;
import edu.cnm.deepdive.pixelcreate.service.repository.UserRepositoryImpl;
import javax.inject.Singleton;

/**
 * Provides Hilt dependency injection configuration for repository interfaces and implementations.
 */
@InstallIn(SingletonComponent.class)
@Module
public abstract class RepositoryModule {

  @Binds
  @Singleton
  abstract UserRepository bindUserRepository(UserRepositoryImpl impl);

  @Binds
  @Singleton
  abstract ProjectRepository bindProjectRepository(ProjectRepositoryImpl impl);

  @Binds
  @Singleton
  abstract PaletteRepository bindPaletteRepository(PaletteRepositoryImpl impl);

}
