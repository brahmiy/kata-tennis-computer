package com.kata.tennisscorecomputer.domain.models;

public enum Point {
    ZERO("0"), FIFTEEN("15"), THIRTY("30"), FORTY("40"), ADVANTAGE("ADV");

    public final String value;

    private Point(String value) {
        this.value = value;
    }
}
