package com.company.catalog.dao;
import java.util.HashMap;

import com.company.catalog.bean.News;
import com.company.catalog.dao.exception.DAOException;

public interface NewsDAO {
    public boolean addNewsInFile(News news)  throws DAOException;
    public HashMap<News, String> readAllNewsInFile()  throws DAOException;
}
