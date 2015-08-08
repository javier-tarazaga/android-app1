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
 * Class that represents a Link in the domain layer.
 */
public class Link {

  private final String href;
  private final boolean templated;

  public Link(String href, boolean templated) {
    this.href = href;
    this.templated = templated;
  }

  public String getHref() {
    return href;
  }

  public boolean isTemplated() {
    return templated;
  }

  @Override public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("***** Link Entity Details *****\n");
    stringBuilder.append("href=" + this.getHref() + "\n");
    stringBuilder.append("templated=" + this.isTemplated() + "\n");
    stringBuilder.append("*******************************");

    return stringBuilder.toString();
  }
}