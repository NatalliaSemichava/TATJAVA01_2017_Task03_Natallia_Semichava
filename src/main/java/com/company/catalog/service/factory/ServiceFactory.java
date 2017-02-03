package com.company.catalog.service.factory;


import com.company.catalog.service.NewsService;
import com.company.catalog.service.impl.NewsServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final NewsService newsService = new NewsServiceImpl();
    private ServiceFactory(){}
    public static ServiceFactory getInstance(){
        return instance;
    }
    public NewsService getNewsService(){
        return newsService;
    }
}
