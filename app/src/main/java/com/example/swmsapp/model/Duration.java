package com.example.swmsapp.model;

import com.google.gson.annotations.SerializedName;

public class Duration {

    @SerializedName("text")
    private String text;

    @SerializedName("value")
    private int value;

    public Duration(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
