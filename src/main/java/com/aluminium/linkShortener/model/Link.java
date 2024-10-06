package com.aluminium.linkShortener.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "links")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Link {
    @Id
    private Long id;

    @Column(nullable = false)
    private String url;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private Link(Long id, String url) {
        this.id = id;
        this.url = url;
        this.createdAt = LocalDateTime.now();
    }

    public static LinkBuilder builder(Long id, String url) {
        return new LinkBuilder(id, url);
    }

    public static class LinkBuilder {
        private final Long id;
        private final String url;

        private LinkBuilder(Long id, String url) {
            this.id = id;
            this.url = url;
        }

        public Link build() {
            return new Link(this.id, this.url);
        }
    }
}
