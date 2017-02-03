package com.company.catalog.service.impl;


import com.company.catalog.bean.Album;
import com.company.catalog.bean.Book;
import com.company.catalog.bean.Movie;
import com.company.catalog.bean.News;
import com.company.catalog.dao.NewsDAO;
import com.company.catalog.dao.exception.DAOException;
import com.company.catalog.dao.factory.DAOFactory;
import com.company.catalog.service.NewsService;
import com.company.catalog.service.exception.ServiceException;
import java.util.HashMap;

public class NewsServiceImpl implements NewsService{

    public boolean addNews(String data) throws ServiceException {
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            NewsDAO newsDAO = daoObjectFactory.getNewsDAO();
            if (newsDAO.addNewsInFile(convertToNews(data))){
                return true;
            }
        }
        catch (DAOException e){
            throw new ServiceException();
        }
        return false;
    }
    public String searchNews(String criterion, String data) throws ServiceException{
        if((criterion.equals("date")) || (criterion.equals("country")) || (criterion.equals("genre"))) {
            String result="";
            try {
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                NewsDAO newsDAO = daoObjectFactory.getNewsDAO();
                HashMap<News, String> news = newsDAO.readAllNewsInFile();

            for (News n : news.keySet()) {
                if (searchCriterion(n,data)) {
                    result += n.toString() + System.lineSeparator();
                }
            }
            }
            catch (DAOException e){
                throw new ServiceException();
            }
            return result;
        }
        else{
            throw new ServiceException();
        }
    }

    private News convertToNews(String data){
        String[] s = data.split("\"");
        switch (s[0].charAt(0)){
            case 'a':
                Album album = new Album(s[5],s[6]);
                setStandardInfo(album,s);
                return album;
            case 'b':
                Book book = new Book(s[5],s[6]);
                setStandardInfo(book,s);
                return book;
            case 'm':
                Movie movie = new Movie(s[5],s[6]);
                setStandardInfo(movie,s);
                return movie;
        }
        return null;
    }

    private News setStandardInfo(News news, String[] s){
        news.setDate(s[1]);
        news.setGenre(s[2]);
        news.setDescription(s[3]);
        news.setCountry(s[4]);
        return news;
    }

    private boolean searchCriterion(News news, String data){
        if ((news.getGenres().equals(data)) || ((news.getDate().equals(data))) || (news.getCountry().equals(data))) {
            return true;
        }
        else{
            return false;
        }
    }
}
