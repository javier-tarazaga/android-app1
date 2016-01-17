package com.tinygrip.android.domain.model;

import java.util.Collection;

/**
 * Model in the domain layer representing a data page. This is a generic class for
 * any item type of items which will be returned in a page format
 */
public class DataPage<T> {

    private final int totalAmount;
    private final Collection<T> items;

    private Link next;
    private Link prev;

    public DataPage(int totalAmount, Collection<T> items) {
        this.totalAmount = totalAmount;
        this.items = items;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public Collection<T> getItems() {
        return items;
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }

    public Link getPrev() {
        return prev;
    }

    public void setPrev(Link prev) {
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
