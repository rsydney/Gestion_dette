package com.core;


import com.ism.services.ClientServiceImpl;
import com.ism.services.ArticleServiceImpl;
import com.ism.services.UserServiceImpl;
import com.ism.views.ClientView;

public class FactoryViews {
        ClientView clientView= null;
        
    public  ClientView getInstanceClientView(){
        if(clientView==null){
            clientView=new ClientView();
        }
        return clientView;
    }
   /* public  ArticleView getInstanceArticleView(){
        if(articleView==null){
            articleView=new  ArticleView();
        }
        return articleView;
    }*/
}
