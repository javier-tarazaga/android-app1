/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tinygrip.android.data.entity;

/**
 * Preview Area Entity used in the data layer.
 */
public class PreviewAreaEntity {

  private String name;
  private float rating;
  private LocationEntity location;
  private LinkEntity self;

  public PreviewAreaEntity() {
    //empty
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getRating() {
    return rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }

  public LocationEntity getLocation() {
    return location;
  }

  public void setLocation(LocationEntity location) {
    this.location = location;
  }

  public LinkEntity getSelf() {
    return self;
  }

  public void setSelf(LinkEntity self) {
    this.self = self;
  }

  @Override public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("***** Preview Area Entity Details *****\n");
    stringBuilder.append("name=" + this.getName() + "\n");
    stringBuilder.append("rating=" + this.getRating() + "\n");
    stringBuilder.append("location=" + this.getLocation() + "\n");
    stringBuilder.append("self=" + this.getSelf() + "\n");
    stringBuilder.append("*******************************");

    return stringBuilder.toString();
  }
}
