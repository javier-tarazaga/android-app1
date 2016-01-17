package com.tinygrip.android.presentation.model.area.route;

import com.tinygrip.android.domain.model.area.route.Path;
import com.tinygrip.android.domain.model.area.route.PreviewRoute;
import java.util.Collection;

/**
 * Preview Route used in the presentation layer.
 */
public class PreviewRouteModel {

    private final PreviewRoute previewRoute;
    private final String name;
    private final Collection<Path> path;
    private final String grade;
    private final String type;

    public PreviewRouteModel(PreviewRoute previewRoute, String name, Collection<Path> path, String grade, String type) {
        this.previewRoute = previewRoute;
        this.name = name;
        this.path = path;
        this.grade = grade;
        this.type = type;
    }

    public PreviewRoute getPreviewRoute() {
        return previewRoute;
    }

    public String getName() {
        return name;
    }

    public Collection<Path> getPath() {
        return path;
    }

    public String getGrade() {
        return grade;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Preview Route Presentation Details *****\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("path=" + this.getPath() + "\n");
        stringBuilder.append("grade=" + this.getGrade() + "\n");
        stringBuilder.append("type=" + this.getType() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }

}
