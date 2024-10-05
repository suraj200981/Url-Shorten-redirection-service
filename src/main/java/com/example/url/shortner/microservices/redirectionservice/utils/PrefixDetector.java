package com.example.url.shortner.microservices.redirectionservice.utils;

import com.example.url.shortner.microservices.redirectionservice.model.UrlDTO;
import org.springframework.stereotype.Component;

@Component
public class PrefixDetector {

    public PrefixDetector() {
    }

    public String prefixDetection(UrlDTO urlDTO){

        if(urlDTO.getOriginalUrl().contains("https://") || urlDTO.getOriginalUrl().contains("http://")){
            return urlDTO.getOriginalUrl();
        }else{
            return urlDTO.getPrefix() + urlDTO.getOriginalUrl();
        }
    }
}
