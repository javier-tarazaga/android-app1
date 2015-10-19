
package com.tinygrip.android.data.entity.mapper;

import com.tinygrip.android.data.entity.RootEntity;
import com.tinygrip.android.domain.Root;
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
  public Root transform(RootEntity rootEntity) {
    Root root = null;
    if (rootEntity != null) {
      root = new Root();
    }

    return root;
  }
}
