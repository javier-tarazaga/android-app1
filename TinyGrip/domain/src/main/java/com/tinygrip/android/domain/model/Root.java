
package com.tinygrip.android.domain.model;

/**
 * Class that represents a Root in the domain layer. At the moment there is no useful information
 * in the presentation layer for it that is is why it is an empty object.
 */
public class Root {

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Root Details *****\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}