package com.example.url.shortner.microservices.redirectionservice.services;

import com.example.url.shortner.microservices.redirectionservice.model.UrlDTO;
import com.example.url.shortner.microservices.redirectionservice.repository.RedirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindURLInDB {

    @Autowired
    RedirectionRepository repo;
    public UrlDTO findUrl(String shortenedUrl){
        UrlDTO urlDTO = repo.findByShortenedUrl(shortenedUrl);
         if(urlDTO==null){
             throw new RuntimeException("URL was not found");
         }else{
             return urlDTO;
         }
    }

}
