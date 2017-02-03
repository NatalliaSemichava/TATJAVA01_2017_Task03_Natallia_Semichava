package com.company.catalog.bean;

public abstract class News {
    private String date;
    private String country;
    private String genre;
    private String description;

    public String toString(){
        return "; date: " + date + "; country: " + country + "; genre: " + genre + "; description: " + description;
    }
    //---------------------------------setters & getters

    public void setDate(String date){
        this.date=date;
    }

    public void setCountry(String country){
        this.country=country;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public void setGenre(String genre){
        this.genre=genre;
    }

    public String getDate(){
        return date;
    }

    public String getCountry(){
        return country;
    }

    public String getDescription(){
        return description;
    }

    public String getGenres(){
        return genre;
    }
}
