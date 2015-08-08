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

import com.tinygrip.android.data.entity.DataPageEntity;
import com.tinygrip.android.data.entity.PreviewAreaEntity;
import com.tinygrip.android.data.entity.RootEntity;
import com.tinygrip.android.data.entity.UserEntity;
import com.tinygrip.android.domain.PreviewArea;
import java.util.List;
import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface RootDataStore {
  /**
   * Get an {@link Observable} which will emit a List of {@link UserEntity}.
   */
  Observable<RootEntity> rootEntity(String apiKey);

  /**
   * Get an {@link Observable} which will emit a {@link DataPageEntity<PreviewArea>}
   */
  Observable<DataPageEntity<PreviewArea>> previewAreasPageEntity();
}
