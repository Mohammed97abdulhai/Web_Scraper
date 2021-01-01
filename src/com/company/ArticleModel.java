package com.company;

public class ArticleModel {
    private String title;
    private String details;
    private String category;

    public ArticleModel(String title, String details, String category) {
        this.title = title;
        this.details = details;
        this.category = category;
    }

    public String getDetails() {
        return details;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }
}
