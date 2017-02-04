package com.ochoa.arnau.memory;

/**
 * Created by arnau on 03/02/2017.
 */
public class Card {

    Integer value;

    boolean selected = false;

    public Card(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
