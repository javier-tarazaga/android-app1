
package com.tinygrip.android.data.repository;

import com.tinygrip.android.data.entity.DataPageEntity;
import com.tinygrip.android.data.entity.area.AreaEntity;
import com.tinygrip.android.data.entity.area.PreviewAreaEntity;
import com.tinygrip.android.data.entity.mapper.AreaEntityDataMapper;
import com.tinygrip.android.data.repository.datasource.area.AreaDataStore;
import com.tinygrip.android.data.repository.datasource.area.AreaDataStoreFactory;
import com.tinygrip.android.domain.model.DataPage;
import com.tinygrip.android.domain.model.area.Area;
import com.tinygrip.android.domain.model.area.PreviewArea;
import com.tinygrip.android.domain.repository.AreaRepository;
import com.tinygrip.android.domain.repository.RootRepository;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.functions.Func1;

/**
 * {@link AreaDataRepository} for retrieving Area data.
 */
@Singleton
public class AreaDataRepository implements AreaRepository {

    private final AreaDataStoreFactory dataStoreFactory;
    private final AreaEntityDataMapper entityDataMapper;

    /**
     * Constructs a {@link RootRepository}.
     *
     * @param dataStoreFactory A factory to construct different data source implementations.
     * @param areaEntityDataMapper {@link AreaEntityDataMapper}.
     */
    @Inject
    public AreaDataRepository(AreaDataStoreFactory dataStoreFactory, AreaEntityDataMapper areaEntityDataMapper) {
        this.dataStoreFactory = dataStoreFactory;
        this.entityDataMapper = areaEntityDataMapper;
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<DataPage<PreviewArea>> previewAreas() {
        final AreaDataStore areaDataStore = this.dataStoreFactory.create();
        return areaDataStore.previewAreas()
                            .map(new Func1<DataPageEntity<PreviewAreaEntity>, DataPage<PreviewArea>>() {
                                @Override
                                public DataPage<PreviewArea> call(DataPageEntity<PreviewAreaEntity> previewAreas) {
                                    return AreaDataRepository.this.entityDataMapper.transform(previewAreas);
                                }
                            });
    }

    @Override
    public Observable<Area> area(String areaHref) {
        final AreaDataStore areaDataStore = this.dataStoreFactory.create();
        return areaDataStore.area(areaHref)
                            .map(new Func1<AreaEntity, Area>() {
                                @Override
                                public Area call(AreaEntity areaEntity) {
                                    return AreaDataRepository.this.entityDataMapper.transform(areaEntity);
                                }
                            });
    }
}
