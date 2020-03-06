package com.example.demo.model;

import java.util.Objects;

public class Article {
    private int id=0;
    private int userId;
    private String title;
    private String description;
    private String date;


    public Article(int id, int userId, String title, String description, String date) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Article(String title) {
        this.title = title;
        id++;
    }

    public Article(String title, String description) {
        this.title = title;
        this.description = description;
        id++;

    }

    public Article() {
        id++;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id &&
                userId == article.userId &&
                Objects.equals(title, article.title) &&
                Objects.equals(description, article.description) &&
                Objects.equals(date, article.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, description, date);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
