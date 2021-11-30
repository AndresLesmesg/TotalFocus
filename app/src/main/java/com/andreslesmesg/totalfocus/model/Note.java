package com.andreslesmesg.totalfocus.model;

public class Note {

    private String title;
    private int category;
    private Boolean favorite;
    public final String[] CATEGORIES = {"Arte", "Cocina", "Tecnología", "Manualidades", "Capacitación"};

    public Note(String title, int category) {
        this.title = title;
        this.category = category;
    }

    public Note(String title, int category, Boolean favorite) {
        this.title = title;
        this.category = category;
        this.favorite = favorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return CATEGORIES[category];
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
}
