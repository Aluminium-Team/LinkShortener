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
    private String id;

    @Column(nullable = false)
    private String url;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private Link(String id, String url) {
        this.id = id;
        this.url = url;
        this.createdAt = LocalDateTime.now();
    }

    public static LinkBuilder builder(String id, String url) {
        return new LinkBuilder(id, url);
    }

    public static class LinkBuilder {
        private final String id;
        private final String url;

        private LinkBuilder(String id, String url) {
            this.id = id;
            this.url = url;
        }

        public Link build() {
            return new Link(this.id, this.url);
        }
    }
}
