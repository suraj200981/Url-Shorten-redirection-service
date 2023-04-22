package com.example.url.shortner.microservices.redirectionservice.controller;

import com.example.url.shortner.microservices.redirectionservice.model.UrlDTO;
import com.example.url.shortner.microservices.redirectionservice.repository.RedirectionRepository;
import com.example.url.shortner.microservices.redirectionservice.services.FindURLInDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RestController
public class RedirectController {
    @Autowired
    private RedirectionRepository redirectionRepository;

    private final String rootURL = "localhost:8200/";

    @Autowired
    FindURLInDB findURLInDB;


    @RequestMapping("/{code}")
    public RedirectView RequestRedirection(@PathVariable String code) {

        UrlDTO urlDTO = findURLInDB.findUrl(rootURL + code);

        System.out.println("Redirecting " + urlDTO.getShortenedUrl() + " to " + urlDTO.getOriginalUrl());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("https://" + urlDTO.getOriginalUrl());
        urlDTO.setClickCount(urlDTO.getClickCount() + 1);
        redirectionRepository.save(urlDTO);
        log.info("tracking updated");

        return redirectView;

    }
}
