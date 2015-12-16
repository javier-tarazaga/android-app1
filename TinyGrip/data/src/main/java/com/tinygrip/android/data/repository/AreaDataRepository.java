
package com.tinygrip.android.data.repository;

import com.tinygrip.android.data.entity.mapper.RootEntityDataMapper;
import com.tinygrip.android.data.repository.datasource.root.RootDataStoreFactory;
import com.tinygrip.android.domain.model.PreviewArea;
import com.tinygrip.android.domain.repository.AreaRepository;
import com.tinygrip.android.domain.repository.RootRepository;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

/**
 * {@link AreaDataRepository} for retrieving Area data.
 */
@Singleton
public class AreaDataRepository implements AreaRepository {

    private final RootDataStoreFactory dataStoreFactory;
    private final RootEntityDataMapper entityDataMapper;

    /**
     * Constructs a {@link RootRepository}.
     *
     * @param dataStoreFactory A factory to construct different data source implementations.
     * @param rootEntityDataMapper {@link RootEntityDataMapper}.
     */
    @Inject
    public AreaDataRepository(RootDataStoreFactory dataStoreFactory, RootEntityDataMapper rootEntityDataMapper) {
        this.dataStoreFactory = dataStoreFactory;
        this.entityDataMapper = rootEntityDataMapper;
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<PreviewArea> previewAreas() {
        // Do not implement it for now
        return null;
    }
}
