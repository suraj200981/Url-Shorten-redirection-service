package com.example.url.shortner.microservices.redirectionservice.controller;

import com.example.url.shortner.microservices.redirectionservice.model.UrlDTO;
import com.example.url.shortner.microservices.redirectionservice.services.FindURLInDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RedirectController {

        @Autowired
        FindURLInDB findURLInDB;
    @RequestMapping("/redirect")
    public RedirectView RequestRedirection(@RequestBody UrlDTO urlDTO){

        System.out.println("Redirecting "+urlDTO.getShortenedUrl()+" to "+ urlDTO.getOriginalUrl());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("https://www.google.com");

        return redirectView;

    }

    @RequestMapping("/redirectme")
    public RedirectView RequestRedirection(@RequestBody String shortUrl){
        UrlDTO urlDTO = new UrlDTO();
        urlDTO= findURLInDB.findUrl(shortUrl);

        System.out.println("Redirecting "+shortUrl+" to"+urlDTO.getOriginalUrl());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(urlDTO.getOriginalUrl());

        return redirectView;

    }
}
