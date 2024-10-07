package com.aluminium.linkShortener.service;

import com.aluminium.linkShortener.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateService {

    @Autowired
    LinkRepository linkRepository;

    public String generateLink(String link) {
        // Do you Magic 3am Eyad

        return "";
    }
}
