package com.andreslesmesg.totalfocus.model;

public class Note {

    private String title, content;
    private int category;
    private boolean favorite;
    public final String[] CATEGORIES = {"Arte", "Cocina", "Tecnología", "Manualidades", "Capacitación"};

    public Note(String title, int category) {
        this.title = title;
        this.category = category;
        this.favorite = false;
        this.content = "";
    }

    public Note(String title, String content , int category) {
        this.title = title;
        this.category = category;
        this.favorite = false;
        this.content = content;
    }

    public Note(String title, String content, int category, boolean favorite) {
        this.title = title;
        this.category = category;
        this.favorite = favorite;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getCategory() {
        return CATEGORIES[category];
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

}
