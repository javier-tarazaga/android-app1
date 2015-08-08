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
 * Entity in the data layer representing a data page in the backend. This is a generic class for
 * any item type of items which will be returned in a page format
 */
public class DataPageEntity<T> {

  private int totalAmount;
  private T items;
  private LinkEntity next;
  private LinkEntity prev;

  public DataPageEntity() {
    //empty
  }

  public int getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(int totalAmount) {
    this.totalAmount = totalAmount;
  }

  public T getItems() {
    return items;
  }

  public void setItems(T items) {
    this.items = items;
  }

  public LinkEntity getNext() {
    return next;
  }

  public void setNext(LinkEntity next) {
    this.next = next;
  }

  public LinkEntity getPrev() {
    return prev;
  }

  public void setPrev(LinkEntity prev) {
    this.prev = prev;
  }

  @Override public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("***** Data Page Entity Details *****\n");
    stringBuilder.append("total amount=" + this.getTotalAmount() + "\n");
    stringBuilder.append("items=" + this.getItems() + "\n");
    stringBuilder.append("next=" + this.getNext() + "\n");
    stringBuilder.append("prev=" + this.getPrev() + "\n");
    stringBuilder.append("*******************************");

    return stringBuilder.toString();
  }
}
