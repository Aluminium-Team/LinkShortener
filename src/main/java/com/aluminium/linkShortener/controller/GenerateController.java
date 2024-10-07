package com.aluminium.linkShortener.controller;

import com.aluminium.linkShortener.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GenerateController {

    @Autowired
    GenerateService generateService;

    @PostMapping("/{link}")
    public ResponseEntity<String> generateShortLink(String link) {

        String generatedLink = generateService.generateLink(link);

        return new ResponseEntity<String>(generatedLink, HttpStatus.CREATED);

    }
}
