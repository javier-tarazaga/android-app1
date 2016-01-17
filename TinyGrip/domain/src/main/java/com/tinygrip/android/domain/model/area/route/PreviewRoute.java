package com.tinygrip.android.domain.model.area.route;

import com.tinygrip.android.domain.model.Link;
import java.util.Collection;

/**
 * Preview Route used in the domain layer.
 */
public class PreviewRoute {

    private String name;
    private Collection<Path> path;
    private String grade;
    private String type;
    private Link wall;
    private Link self;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Path> getPath() {
        return path;
    }

    public void setPath(Collection<Path> path) {
        this.path = path;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Link getWall() {
        return wall;
    }

    public void setWall(Link wall) {
        this.wall = wall;
    }

    public Link getSelf() {
        return self;
    }

    public void setSelf(Link self) {
        this.self = self;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Preview Route Details *****\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("path=" + this.getPath() + "\n");
        stringBuilder.append("grade=" + this.getGrade() + "\n");
        stringBuilder.append("type=" + this.getType() + "\n");
        stringBuilder.append("wall=" + this.getWall() + "\n");
        stringBuilder.append("self=" + this.getSelf() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }

}
