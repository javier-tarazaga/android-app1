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
 * Root Entity used in the data layer.
 */
public class RootEntity {

  private LinkEntity previewAreas;
  private LinkEntity areas;

  public RootEntity() {
    //empty
  }

  public LinkEntity getPreviewAreas() {
    return previewAreas;
  }

  public void setPreviewAreas(LinkEntity previewAreas) {
    this.previewAreas = previewAreas;
  }

  public LinkEntity getAreas() {
    return areas;
  }

  public void setAreas(LinkEntity areas) {
    this.areas = areas;
  }

  @Override public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("***** Root Entity Details *****\n");
    stringBuilder.append("previewAreas=" + this.getPreviewAreas() + "\n");
    stringBuilder.append("areas=" + this.getAreas() + "\n");
    stringBuilder.append("*******************************");

    return stringBuilder.toString();
  }
}
