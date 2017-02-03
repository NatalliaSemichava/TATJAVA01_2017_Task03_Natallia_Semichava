package com.company.catalog.dao.impl;

import com.company.catalog.bean.Album;
import com.company.catalog.bean.Book;
import com.company.catalog.bean.Movie;
import com.company.catalog.bean.News;
import com.company.catalog.dao.NewsDAO;
import com.company.catalog.dao.exception.DAOException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNewsDAO implements NewsDAO {
    private ArrayList<String> newsFromFile = new ArrayList<String>();
    private HashMap<News, String> allNews = new HashMap<News, String>();

    public boolean addNewsInFile(News news) throws DAOException {

        try{
            FileWriter fw = new FileWriter("./News.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println(news.toString());
            out.close();
            return true;
        }
        catch (IOException e){
            throw new DAOException();
        }
    }

    public HashMap<News, String> readAllNewsInFile() throws DAOException {
        convertNewsToList();
        return allNews;
    }

    private void readFile(String path) throws DAOException {
        allNews.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String S;
            while ((S = br.readLine()) != null) {
                newsFromFile.add(S);
            }
            br.close();
        }
        catch (IOException e){
            throw new DAOException();
        }
    }

    private void convertNewsToList() throws DAOException {

        readFile("./News.txt");
        String category = "";
        for (String str : newsFromFile) {

            Matcher matcher1 = Pattern.compile("category:").matcher(str);
            Matcher matcher2 = Pattern.compile(";").matcher(str);
            if ((matcher2.find()) && (matcher1.find())) {
                category = str.substring(matcher1.end(), matcher2.start()).trim();
            }
            switch (category.charAt(0)) {
                case 'm':
                    String director = getInfo("director: ", "; duration: ", str);
                    String duration1 = getInfo("duration: ", "; date", str);
                    Movie movie = new Movie(director, duration1);

                    setStandardInfo(movie, str);

                    allNews.put(movie, category);
                    break;
                case 'a':
                    String author1 = getInfo("author: ", "; duration: ", str);
                    String duration2 = getInfo("duration: ", "; date", str);
                    Album album = new Album(author1, duration2);

                    setStandardInfo(album, str);

                    allNews.put(album, category);
                    break;
                case 'b':
                    String author2 = getInfo("author: ", "; publisher: ", str);
                    String publisher = getInfo("publisher: ", "; date", str);
                    Book book = new Book(author2, publisher);

                    setStandardInfo(book, str);

                    allNews.put(book, category);

                    break;
            }
        }
    }

    private String getInfo(String beginString, String endString, String s) {
        Matcher matcher1 = Pattern.compile(beginString).matcher(s);
        Matcher matcher2 = Pattern.compile(endString).matcher(s);
        if ((matcher1.find()) && (matcher2.find())) {
            return s.substring(matcher1.end(), matcher2.start()).trim();
        }
        return "";
    }

    private String getInfo(String beginString, String s) {
        Matcher matcher1 = Pattern.compile(beginString).matcher(s);
        if (matcher1.find()) {
            return s.substring(matcher1.end()).trim();
        }
        return "";
    }

    private News setStandardInfo(News news, String str) {
        news.setDate(getInfo("date: ", "; country: ", str));
        news.setCountry(getInfo("country: ", "; genre", str));
        news.setGenre(getInfo("genre: ", "; description", str));
        news.setDescription(getInfo("description: ", str));
        return news;
    }
}