package com.company.catalog.bean;

public class Book extends News {

    private String author;
    private String publisher;

    public Book(String author, String publisher){
        this.author = author;
        this.publisher = publisher;
    }

    public String toString(){

        return "category: b; author: " + author + "; publisher: " + publisher + super.toString();
    }

}
