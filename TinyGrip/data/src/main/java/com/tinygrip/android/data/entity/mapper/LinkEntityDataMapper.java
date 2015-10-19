
package com.tinygrip.android.data.entity.mapper;

import com.tinygrip.android.data.entity.LinkEntity;
import com.tinygrip.android.data.entity.RootEntity;
import com.tinygrip.android.data.entity.UserEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link LinkEntity} (in the data layer) to {@link com.tinygrip.android
 * .domain.Link} in the domain layer.
 */
@Singleton
public class LinkEntityDataMapper {

  @Inject
  public LinkEntityDataMapper() {}

  /**
   * Transform a {@link LinkEntity} into an {@link com.tinygrip.android.domain.Link}.
   *
   * @param linkEntity Object to be transformed.
   * @return {@link com.tinygrip.android.domain.Link} if valid {@link LinkEntity} otherwise null.
   */
  public com.tinygrip.android.domain.Link transform(LinkEntity linkEntity) {
    com.tinygrip.android.domain.Link link = null;
    if (linkEntity != null) {
      link = new com.tinygrip.android.domain.Link(linkEntity.getHref(), linkEntity.isTemplated());
    }

    return link;
  }
}
