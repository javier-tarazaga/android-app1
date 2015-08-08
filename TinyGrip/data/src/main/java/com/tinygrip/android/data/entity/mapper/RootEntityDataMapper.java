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
package com.tinygrip.android.data.entity.mapper;

import com.tinygrip.android.data.entity.RootEntity;
import com.tinygrip.android.data.entity.UserEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link RootEntity} (in the data layer) to {@link com.tinygrip.android
 * .domain.Root} in the domain layer.
 */
@Singleton
public class RootEntityDataMapper {

  private final LinkEntityDataMapper linkEntityDataMapper;

  @Inject
  public RootEntityDataMapper(LinkEntityDataMapper linkEntityDataMapper) {
    this.linkEntityDataMapper = linkEntityDataMapper;
  }

  /**
   * Transform a {@link RootEntity} into an {@link com.tinygrip.android.domain.User}.
   *
   * @param rootEntity Object to be transformed.
   * @return {@link com.tinygrip.android.domain.Root} if valid {@link RootEntity} otherwise null.
   */
  public com.tinygrip.android.domain.Root transform(RootEntity rootEntity) {
    com.tinygrip.android.domain.Root root = null;
    if (rootEntity != null) {
      root = new com.tinygrip.android.domain.Root(linkEntityDataMapper.transform(rootEntity.getPreviewAreas()));
      root.setAreas(linkEntityDataMapper.transform(rootEntity.getAreas()));
    }

    return root;
  }
}
