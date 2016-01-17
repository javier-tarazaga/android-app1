package com.tinygrip.android.presentation.view.area.map;

import com.tinygrip.android.domain.model.DataPage;
import com.tinygrip.android.presentation.model.area.PreviewAreaModel;

public interface AreaMapView {

    void initializeMap();

    void renderPreviewAreas(DataPage<PreviewAreaModel> model);

    void goToArea(PreviewAreaModel model);
}
