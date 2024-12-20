package com.core;


import java.util.List;

import com.ism.entity.Client;
import com.ism.entity.Dette;
import com.ism.entity.User;
import com.ism.repository.bd.ArticleRepositoryBD;
import com.ism.repository.bd.ClientRepositoryBD;
import com.ism.repository.bd.UserRepositoryBD;
import com.ism.repository.ArticleRepositoryInter;
import com.ism.repository.ClientRepositoryInter;
import com.ism.repository.DetteRepository;
import com.ism.repository.DetteRepositoryInter;
import com.ism.repository.UserRepositoryInter;
import com.ism.repository.ArticleRepository;
import com.ism.repository.ClientRepository;
import com.ism.repository.UserRepository;



public class FactoryRepository {
     
        private  ArticleRepositoryInter articleRepository=null;
        private  ClientRepositoryInter clientRepo=null;
        private  UserRepositoryInter userRepository=null;
        private DetteRepositoryInter detteRepository=null;

        private List<User> users; 
        private List<Client> clients;
        private List<Dette> debts;
        
    public  ClientRepositoryInter getInstanceClientRepository(){
        if(clientRepo==null){
            clientRepo=new ClientRepositoryBD();
        }
        return clientRepo;
    }
    public  UserRepositoryInter  getInstanceUserRepository(){
        if( userRepository==null){
            userRepository= new UserRepository();
            //ClientRepositoryBD clientRepositoryBD = new ClientRepositoryBD();
            //userRepository= new UserRepositoryBD(clientRepositoryBD);
        }
        return  userRepository;
    }
    public  ArticleRepositoryInter  getInstanceArticleRepository(){
        if(articleRepository==null){
            articleRepository= new ArticleRepository();
        }
        return  articleRepository;
    }

    public  DetteRepositoryInter getInstanceDetteRepository(){
        if(detteRepository==null){
            detteRepository= new DetteRepository();
        }
        return  detteRepository;
    }

    //ClientRepositoryBD clientRepositoryBD = new ClientRepositoryBD();
    //UserRepository<User> userRepository= new UserRepositoryBD(clientRepositoryBD);
    
}
