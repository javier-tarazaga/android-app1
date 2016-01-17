package com.tinygrip.android.data.entity.area.route;

import com.tinygrip.android.data.entity.LinkEntity;
import java.util.Collection;

/**
 * Preview Route Wall Entity used in the data layer.
 */
public class PreviewRouteEntity {

    private String name;
    private Collection<PathEntity> path;
    private String grade;
    private String type;
    private LinkEntity wall;
    private LinkEntity self;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<PathEntity> getPath() {
        return path;
    }

    public void setPath(Collection<PathEntity> path) {
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

    public LinkEntity getWall() {
        return wall;
    }

    public void setWall(LinkEntity wall) {
        this.wall = wall;
    }

    public LinkEntity getSelf() {
        return self;
    }

    public void setSelf(LinkEntity self) {
        this.self = self;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Preview Route Entity Details *****\n");
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
