package com.company.catalog.bean;


public class Album extends News {
    private String author;
    private String duration;

    public Album(String author, String duration) {
        this.author = author;
        this.duration = duration;
    }

    public String toString() {
        return "category: a; author: " + author + "; duration: " + duration + super.toString();
    }
}
