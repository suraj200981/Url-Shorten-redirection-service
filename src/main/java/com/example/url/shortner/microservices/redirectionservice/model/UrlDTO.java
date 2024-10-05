package com.example.url.shortner.microservices.redirectionservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Data
@Table(name = "urls")
public class UrlDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "shortened_url")
    private String shortenedUrl;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "click_count")
    private Integer clickCount = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Define many-to-one relationship with User
    @ManyToOne(fetch = FetchType.LAZY, optional = true) // Set optional to true
    @JoinColumn(name = "user_id", nullable = true) // Set nullable to true
    private User user;
}
