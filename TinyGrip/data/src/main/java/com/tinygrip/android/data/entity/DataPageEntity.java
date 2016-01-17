
package com.tinygrip.android.data.entity;

import java.util.Collection;

/**
 * Entity in the data layer representing a data page in the backend. This is a generic class for
 * any item type of items which will be returned in a page format
 */
public class DataPageEntity<T> {

    private int totalAmount;
    private Collection<T> items;
    private LinkEntity next;
    private LinkEntity prev;

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Collection<T> getItems() {
        return items;
    }

    public void setItems(Collection<T> items) {
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

    @Override
    public String toString() {
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
