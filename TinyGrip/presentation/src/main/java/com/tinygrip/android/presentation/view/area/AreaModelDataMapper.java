
package com.tinygrip.android.presentation.view.area;

import android.support.annotation.Nullable;
import com.tinygrip.android.domain.model.DataPage;
import com.tinygrip.android.domain.model.area.Area;
import com.tinygrip.android.domain.model.area.PreviewArea;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.model.area.AreaModel;
import com.tinygrip.android.presentation.model.area.PreviewAreaModel;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Area} and {@link PreviewArea} (in the domain layer) to {@link AreaModel} and
 * {@link PreviewAreaModel} in the presentation layer.
 */
@ActivityScope
public class AreaModelDataMapper {

    @Inject
    public AreaModelDataMapper() {}

    /**
     * Transform a {@link DataPage<PreviewArea>} into an {@link DataPage< PreviewAreaModel>}.
     *
     * @param previewAreas Object to be transformed.
     * @return {@link DataPage<PreviewAreaModel>}.
     */
    @Nullable
    public DataPage<PreviewAreaModel> transform(DataPage<PreviewArea> previewAreas) {
        if (previewAreas == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        DataPage<PreviewAreaModel> previewAreasModel = null;
        if (previewAreas.getItems() != null) {
            previewAreasModel = new DataPage<>(previewAreas.getTotalAmount(), this.transform(previewAreas.getItems()));
        }

        return previewAreasModel;
    }

    /**
     * Transform a Collection of {@link PreviewArea} into a Collection of {@link PreviewAreaModel}.
     *
     * @param previewAreaCollection Objects to be transformed.
     * @return List of {@link PreviewAreaModel}.
     */
    public Collection<PreviewAreaModel> transform(Collection<PreviewArea> previewAreaCollection) {
        Collection<PreviewAreaModel> previewAreaModelCollection = new ArrayList<>();

        if (previewAreaCollection != null && !previewAreaCollection.isEmpty()) {
            for (PreviewArea previewArea : previewAreaCollection) {
                previewAreaModelCollection.add(this.transform(previewArea));
            }
        }

        return previewAreaModelCollection;
    }

    @Nullable
    public PreviewAreaModel transform(PreviewArea previewArea) {
        PreviewAreaModel model = null;
        if (previewArea != null) {
            model = new PreviewAreaModel(
                previewArea,
                previewArea.getName(),
                previewArea.getLocation()
            );
        }

        return model;
    }
}
