package com.example.url.shortner.microservices.redirectionservice.controller;

import com.example.url.shortner.microservices.redirectionservice.kafka.KafkaProducerService;
import com.example.url.shortner.microservices.redirectionservice.model.UrlDTO;
import com.example.url.shortner.microservices.redirectionservice.repository.RedirectionRepository;
import com.example.url.shortner.microservices.redirectionservice.services.FindURLInDB;
import com.example.url.shortner.microservices.redirectionservice.utils.PrefixDetector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
public class RedirectController {
    @Autowired
    private RedirectionRepository redirectionRepository;

    @Autowired
    KafkaProducerService kafkaProducerService;

    @Autowired
    PrefixDetector prefixDetector;


    private final String rootURL = "localhost:8200/";

    @Autowired
    FindURLInDB findURLInDB;


    @RequestMapping("/{code}")
    public ModelAndView RequestRedirection(@PathVariable String code, HttpServletResponse response) {

        UrlDTO urlDTO = findURLInDB.findUrl(rootURL + code);

        System.out.println("Redirecting " + urlDTO.getShortenedUrl() + " to " + urlDTO.getOriginalUrl());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/redirect");
        urlDTO.setClickCount(urlDTO.getClickCount() + 1);
        redirectionRepository.save(urlDTO);

        String orginalUrl = prefixDetector.prefixDetection(urlDTO);
        log.info("tracking updated");

        Cookie originalUrlCookie = new Cookie("originalUrl", orginalUrl);
        originalUrlCookie.setMaxAge(60 * 60 * 24 * 365); // set cookie to expire in 1 year
        response.addCookie(originalUrlCookie);

        Map<String, String> jsonResponse = new HashMap<>();
        jsonResponse.put("originalUrl", orginalUrl);
        ModelAndView modelAndView = new ModelAndView(redirectView);
        modelAndView.addObject("jsonResponse", jsonResponse);

        kafkaProducerService.sendMessage(String.valueOf(urlDTO.getClickCount()), "url-click-topic");
        return modelAndView;
    }


}
