package com.tinygrip.android.presentation.view.navigation.view;

import com.tinygrip.android.domain.model.DataPage;
import com.tinygrip.android.presentation.model.area.PreviewAreaModel;
import com.tinygrip.android.presentation.view.LoadDataView;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing the home tab in the main
 */
public interface HomeView extends LoadDataView {

    /**
     * Start the process of creating a new Area
     */
    void createNewArea();

    void renderPreviewAreas(DataPage<PreviewAreaModel> model);
}
