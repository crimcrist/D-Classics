package com.example.dclassics;

public class AllBookItem {

    public static final String FICTION = "Fiction";
    public static final String NON_FICTION = "Non-Fiction";

    private final String title;
    private final String author;
    private final String description;
    private final int imageRes;
    private final String category;

    public AllBookItem(String title, String author, String description, int imageRes, String category) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.imageRes = imageRes;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getCategory() {
        return category;
    }
}