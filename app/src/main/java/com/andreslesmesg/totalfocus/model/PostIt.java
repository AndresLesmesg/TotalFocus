package com.andreslesmesg.totalfocus.model;

public class PostIt {

    private String title;
    private String detail;
    private boolean favorite;
    private boolean orientation;

    public PostIt(String title, String detail) {
        this.title = title;
        this.detail = detail;
        this.favorite = false;
        this.orientation = false;
    }

    public PostIt(String title, String detail, boolean favorite, boolean orientation) {
        this.title = title;
        this.detail = detail;
        this.favorite = favorite;
        this.orientation = orientation;
    }

    public PostIt(String title, String detail, boolean favorite) {
        this.title = title;
        this.detail = detail;
        this.favorite = favorite;
        this.orientation = false;
    }

    public String getContent(){
        if(orientation){
            return detail;
        }
        return title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean getOrientation() {
        return orientation;
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }
}

