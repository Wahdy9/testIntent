package com.example.testintent;

public class Item {
    String text;
    int icon;

    public Item(String text, int icon) {
        this.text = text;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return text;
    }
}
