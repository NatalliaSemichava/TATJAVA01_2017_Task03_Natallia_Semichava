package com.company.catalog.service;

import com.company.catalog.service.exception.ServiceException;

public interface NewsService {
    public boolean addNews(String data) throws ServiceException;;
    public String searchNews(String criterion, String data) throws ServiceException;;
}
