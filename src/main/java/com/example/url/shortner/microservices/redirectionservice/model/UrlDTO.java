package com.example.url.shortner.microservices.redirectionservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Data
@Table(name = "urls")
public class UrlDTO {
    @Id
    private int id;
    private String originalUrl;
    //hide prefix in database
    private String prefix;
    private int clickCount;

    //    private List<String> ipAddress;
    private Date createdAt;
    private String shortenedUrl;
}
