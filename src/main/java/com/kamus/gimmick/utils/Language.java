package com.kamus.gimmick.utils;

public enum Language {
    INDONESIA("Indonesia"),
    ENGLISH("English");

    private final String displayName;

    Language(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
