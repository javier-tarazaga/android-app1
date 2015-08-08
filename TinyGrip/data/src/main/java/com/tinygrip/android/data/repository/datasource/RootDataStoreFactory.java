/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tinygrip.android.data.repository.datasource;

import com.tinygrip.android.data.service.RootService;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link RootDataStore}.
 */
@Singleton
public class RootDataStoreFactory {

  private final RootService rootService;

  @Inject
  public RootDataStoreFactory(RootService rootService) {
    if (rootService == null) {
      throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
    }
    this.rootService = rootService;
  }

  /**
   * Create {@link UserDataStore} from a user id.
   */
  public RootDataStore create() {
    RootDataStore rootDataStore;

    rootDataStore = createCloudDataStore();
    return rootDataStore;
  }

  /**
   * Create {@link RootDataStore} to retrieve data from the Cloud.
   */
  public RootDataStore createCloudDataStore() {
    return new CloudRootDataStore(rootService);
  }
}
