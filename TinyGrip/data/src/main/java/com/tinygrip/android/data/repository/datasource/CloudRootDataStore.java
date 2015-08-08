/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tinygrip.android.data.repository.datasource;

import com.tinygrip.android.data.entity.DataPageEntity;
import com.tinygrip.android.data.entity.RootEntity;
import com.tinygrip.android.data.service.RootService;
import com.tinygrip.android.domain.PreviewArea;
import rx.Observable;

/**
 * {@link RootDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudRootDataStore implements RootDataStore {

  private final RootService rootService;

  /**
   * Construct a {@link RootDataStore} based on connections to the api (Cloud).
   *
   * @param rootService The {@link RootService} implementation to use.
   */
  public CloudRootDataStore(RootService rootService) {
    this.rootService = rootService;
  }

  @Override public Observable<RootEntity> rootEntity(String apiKey) {
    return this.rootService.rootEntity(apiKey);
  }

  @Override public Observable<DataPageEntity<PreviewArea>> previewAreasPageEntity() {
    return this.rootService.previewAreasPageEntity();
  }
}
