package com.aluminium.linkShortener.controller;

import com.aluminium.linkShortener.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class GenerateController {

    @Autowired
    LinkService linkService;

//    This endpoint is created for testing "not found" error only
    @GetMapping("/")
    public String helloWorld () {

        return "Homepage";

    }

    @PostMapping("/{link}")
    public ResponseEntity<String> generateShortLink(String link) {

        String generatedLink = linkService.shortenLink(link);

        return new ResponseEntity<String>(generatedLink, HttpStatus.CREATED);

    }

    @GetMapping("/{generatedId}")
    public RedirectView redirectToGoogle(@PathVariable String generatedId) throws Exception {
        try {
            String originalLink = linkService.getLink(generatedId);
            return new RedirectView(originalLink);
        } catch (Exception e) {
            return new RedirectView("/");
        }
    }

}
