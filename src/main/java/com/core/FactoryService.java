package com.core;

import com.ism.entity.DebtRequest;
import com.ism.entity.User;
import com.ism.repository.bd.ArticleRepositoryBD;
import com.ism.repository.bd.ClientRepositoryBD;
import com.ism.repository.ArticleRepository;
import com.ism.repository.ClientRepository;
import com.ism.repository.DetteRepository;
import com.ism.repository.UserRepository;
import com.ism.services.ClientServiceImpl;
import com.ism.services.ArticleServiceImpl;
import com.ism.services.UserServiceImpl;
import com.ism.services.ClientServiceImplInter;
import com.ism.services.DetteServiceImpl;
import com.ism.services.UserServiceImplInter;
import com.ism.services.ArticleServiceImplInter;

public class FactoryService {

        UserServiceImpl userServiceImpl= null;
        ClientServiceImpl clientServiceImpl= null;
        ArticleServiceImpl articleServiceImpl=null;
        DetteServiceImpl detteServiceImpl=null;
        DebtRequest debtRequest=null;
        FactoryRepository factoryService = new FactoryRepository();
        UserRepository userRepository=new UserRepository();
        ArticleRepository articleRepository=new ArticleRepository();
        DetteRepository detteRepository=new DetteRepository();
        
    public  UserServiceImpl getInstanceUserServiceImpl(){
        if(userServiceImpl==null){
            userServiceImpl=new UserServiceImpl(userRepository, articleRepository, detteRepository);
        }
        return userServiceImpl;
    }
    public ClientServiceImpl  getInstanceClientService(){
        if(clientServiceImpl==null){
            clientServiceImpl= new ClientServiceImpl();
        }
        return  clientServiceImpl;
    }
    public   ArticleServiceImpl getInstanceArticleServiceImpl(){
        if(articleServiceImpl==null){
            articleServiceImpl= new ArticleServiceImpl();
        }
        return  articleServiceImpl;
    }

    public  DetteServiceImpl getInstanceDetteServiceImpl(){
        if(detteServiceImpl==null){
            detteServiceImpl=new DetteServiceImpl();
        }
        return detteServiceImpl;
    }

    public  DebtRequest getInstanceDebtRequest(){
        if(debtRequest==null){
            debtRequest=new DebtRequest();
        }
        return debtRequest;
    }
}



