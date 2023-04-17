package com.example.url.shortner.microservices.redirectionservice.repository;

import com.example.url.shortner.microservices.redirectionservice.model.UrlDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedirectionRepository extends JpaRepository<UrlDTO,Integer> {

    UrlDTO findByshortenedUrl(String url);

}
