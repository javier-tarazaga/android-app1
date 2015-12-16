
package com.tinygrip.android.data.entity.mapper;

import com.tinygrip.android.data.entity.LinkEntity;
import com.tinygrip.android.domain.model.Link;
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
   * Transform a {@link LinkEntity} into an {@link Link}.
   *
   * @param linkEntity Object to be transformed.
   * @return {@link Link} if valid {@link LinkEntity} otherwise null.
   */
  public Link transform(LinkEntity linkEntity) {
    Link link = null;
    if (linkEntity != null) {
      link = new Link(linkEntity.getHref(), linkEntity.isTemplated());
    }

    return link;
  }
}
