package com.example.url.shortner.microservices.redirectionservice.controller;

import com.example.url.shortner.microservices.redirectionservice.model.UrlDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {


    @PostMapping("/redirect")
    public void RequestRedirection(@RequestBody UrlDTO urlDTO){

        System.out.println("Redirecting "+urlDTO.getShortenedUrl()+" to "+ urlDTO.getOriginalUrl());

    }

}
