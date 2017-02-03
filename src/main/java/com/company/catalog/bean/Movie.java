package com.company.catalog.bean;


public class Movie extends News {

    private String director;
    private String duration;

    public Movie(String director, String duration){
        this.director = director;
        this.duration = duration;
    }


    public String toString(){
        return "category: m; director: " + director + "; duration: " + duration + super.toString();
    }

}
