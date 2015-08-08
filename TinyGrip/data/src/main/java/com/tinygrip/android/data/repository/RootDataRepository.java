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
package com.tinygrip.android.data.repository;

import com.tinygrip.android.data.entity.RootEntity;
import com.tinygrip.android.data.entity.mapper.RootEntityDataMapper;
import com.tinygrip.android.data.repository.datasource.RootDataStore;
import com.tinygrip.android.data.repository.datasource.RootDataStoreFactory;
import com.tinygrip.android.domain.repository.RootRepository;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.functions.Func1;

/**
 * {@link RootDataRepository} for retrieving user data.
 */
@Singleton
public class  RootDataRepository implements RootRepository {

  private final RootDataStoreFactory dataStoreFactory;
  private final RootEntityDataMapper entityDataMapper;

  /**
   * Constructs a {@link RootRepository}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   * @param rootEntityDataMapper {@link RootEntityDataMapper}.
   */
  @Inject
  public RootDataRepository(RootDataStoreFactory dataStoreFactory,
      RootEntityDataMapper rootEntityDataMapper) {
    this.dataStoreFactory = dataStoreFactory;
    this.entityDataMapper = rootEntityDataMapper;
  }

  @SuppressWarnings("Convert2MethodRef")
  @Override public Observable<com.tinygrip.android.domain.Root> root(String apiKey) {
    final RootDataStore rootDataStore = this.dataStoreFactory.create();
    return rootDataStore.rootEntity(apiKey).map(new Func1<RootEntity, com.tinygrip.android.domain.Root>() {
      @Override
      public com.tinygrip.android.domain.Root call(RootEntity rootEntity) {
        return entityDataMapper.transform(rootEntity);
      }
    });
  }
}
