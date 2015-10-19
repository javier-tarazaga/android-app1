
package com.tinygrip.android.data.repository;

import com.tinygrip.android.data.entity.RootEntity;
import com.tinygrip.android.data.entity.mapper.RootEntityDataMapper;
import com.tinygrip.android.data.repository.datasource.root.RootDataStore;
import com.tinygrip.android.data.repository.datasource.root.RootDataStoreFactory;
import com.tinygrip.android.domain.Root;
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

  private final RootDataStoreFactory rooDataStoreFactory;
  private final RootEntityDataMapper entityDataMapper;

  /**
   * Constructs a {@link RootRepository}.
   *
   * @param rootDataStoreFactory A factory to construct different data source implementations.
   * @param rootEntityDataMapper {@link RootEntityDataMapper}.
   */
  @Inject
  public RootDataRepository(RootDataStoreFactory rootDataStoreFactory,
      RootEntityDataMapper rootEntityDataMapper) {
    this.rooDataStoreFactory = rootDataStoreFactory;
    this.entityDataMapper = rootEntityDataMapper;
  }

  @SuppressWarnings("Convert2MethodRef")
  @Override public Observable<Root> root(String apiKey) {
    final RootDataStore rootDataStore = this.rooDataStoreFactory.create();
    return rootDataStore.rootEntity(apiKey).map(new Func1<RootEntity, Root>() {
      @Override
      public Root call(RootEntity rootEntity) {
        return entityDataMapper.transform(rootEntity);
      }
    });
  }
}
