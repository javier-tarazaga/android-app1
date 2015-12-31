
package com.tinygrip.android.data.entity;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity in the data layer representing a data page in the backend. This is a generic class for
 * any item type of items which will be returned in a page format
 */
public class DataPageEntity<T> {

    @Getter @Setter private int totalAmount;

    @Getter @Setter private Collection<T> items;

    @Getter @Setter private LinkEntity next;

    @Getter @Setter private LinkEntity prev;

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
