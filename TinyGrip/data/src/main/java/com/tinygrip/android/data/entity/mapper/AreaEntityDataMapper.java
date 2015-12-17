package com.tinygrip.android.data.entity.mapper;

import com.tinygrip.android.data.entity.DataPageEntity;
import com.tinygrip.android.domain.model.DataPage;
import com.tinygrip.android.domain.model.PreviewArea;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform any related Area entities into Domain layer models
 */
@Singleton
public class AreaEntityDataMapper {

    private final LinkEntityDataMapper linkEntityDataMapper;

    @Inject
    public AreaEntityDataMapper(LinkEntityDataMapper linkEntityDataMapper) {
        this.linkEntityDataMapper = linkEntityDataMapper;
    }

    /**
     * Transform a {@link DataPageEntity<PreviewArea>} into an {@link DataPage<PreviewArea>}.
     *
     * @param previewAreasDataPageEntity Object to be transformed.
     * @return {@link DataPage<PreviewArea>} if valid {@link DataPageEntity<PreviewArea>} otherwise null.
     */
    public DataPage<PreviewArea> transform(DataPageEntity<PreviewArea> previewAreasDataPageEntity) {
        DataPage<PreviewArea> previewAreaDataPage = null;
        if (previewAreasDataPageEntity != null) {
            previewAreaDataPage = new DataPage<>(previewAreasDataPageEntity.getTotalAmount(), previewAreasDataPageEntity.getItems());
            previewAreaDataPage.setNext(this.linkEntityDataMapper.transform(previewAreasDataPageEntity.getNext()));
            previewAreaDataPage.setPrev(this.linkEntityDataMapper.transform(previewAreasDataPageEntity.getPrev()));
        }

        return previewAreaDataPage;
    }
}
