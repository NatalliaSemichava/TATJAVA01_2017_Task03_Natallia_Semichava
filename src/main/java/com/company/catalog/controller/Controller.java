package com.company.catalog.controller;

import com.company.catalog.controller.exception.ControllerException;
import com.company.catalog.service.NewsService;
import com.company.catalog.service.exception.ServiceException;
import com.company.catalog.service.factory.ServiceFactory;

public class Controller {
    public String executeTask(String request, String data) throws ControllerException {
        String response = "";

        ServiceFactory daoObjectFactory = ServiceFactory.getInstance();
        NewsService newsDAO = daoObjectFactory.getNewsService();

        try {
            switch (request.charAt(0)) {
                case '1':
                    if (newsDAO.addNews(data)){
                        response = "You add this record";
                    }
                    else{
                        response = "Some mistake happend. This record wasn't added";
                    }
                    break;
                case '2':
                    String[] s = data.split(" ");
                    response = newsDAO.searchNews(s[0], s[1]);
                    if (response==""){
                        response = "There is no such record";
                    }
                    break;
                default:
                    response = "We can't execute this command";
            }
        }
        catch (ServiceException e){
            throw new ControllerException();
        }
        return response;
    }
}
