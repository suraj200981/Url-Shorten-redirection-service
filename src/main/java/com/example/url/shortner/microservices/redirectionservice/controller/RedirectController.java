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

//    @RequestMapping("/redirect")
//    public RedirectView RequestRedirection(@RequestBody UrlDTO urlDTO) {
//
//        System.out.println("Redirecting " + urlDTO.getShortenedUrl() + " to " + urlDTO.getOriginalUrl());
//        RedirectView redirectView = new RedirectView();
//        redirectView.setUrl("https://www.google.com");
//
//        return redirectView;
//
//    }

    @RequestMapping("/{code}")
    public RedirectView RequestRedirection(@PathVariable String code) {

        UrlDTO urlDTO = findURLInDB.findUrl("localhost:8200/"+code);

        System.out.println("Redirecting " + urlDTO.getShortenedUrl() + " to " + urlDTO.getOriginalUrl());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("https://"+urlDTO.getOriginalUrl());

        return redirectView;

    }
}
