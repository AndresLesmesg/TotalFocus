package com.andreslesmesg.totalfocus.model;

import android.net.Uri;

public class Course {

    private String title;
    private int category;
    private Uri imageUri;
    public final String[] CATEGORIES = {"Arte", "Cocina", "Tecnología", "Manualidades", "Capacitación"};

    public Course(String title, int category, Uri imageUri) {
        this.title = title;
        this.category = category;
        this.imageUri = imageUri;
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

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
