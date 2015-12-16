package com.tinygrip.android.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Model in the domain layer representing a data page. This is a generic class for
 * any item type of items which will be returned in a page format
 */
public class DataPage<T> {

    @Getter
    @Setter
    private int totalAmount;

    @Getter @Setter private T items;

    @Getter @Setter private Link next;

    @Getter @Setter private Link prev;

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
