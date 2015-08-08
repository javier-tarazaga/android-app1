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
package com.tinygrip.android.domain;

/**
 * Class that represents a Root in the domain layer.
 */
public class Root {

  private final Link previewAreas;

  private Link areas;

  public Root(Link previewAreas) {
    this.previewAreas = previewAreas;
  }

  public Link getPreviewAreas() {
    return previewAreas;
  }

  public Link getAreas() {
    return areas;
  }

  public void setAreas(Link areas) {
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