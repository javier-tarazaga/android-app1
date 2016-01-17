package com.tinygrip.android.presentation.view.area.map;

import com.tinygrip.android.domain.model.DataPage;
import com.tinygrip.android.domain.model.area.PreviewArea;

public interface AreaMapView {

    void initializeMap();

    void renderPreviewAreas(DataPage<PreviewArea> previewAreaDataPage);

    void goToArea(PreviewArea previewArea);
}
