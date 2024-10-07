package com.aluminium.linkShortener.controller;

import com.aluminium.linkShortener.service.GenerateService;
import com.aluminium.linkShortener.service.GetLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class GenerateController {

    @Autowired
    GenerateService generateService;

    @Autowired
    GetLinkService getLinkService;

//    This endpoint is created for testing "not found" error only
    @GetMapping("/")
    public String helloWorld () {

        return "Homepage";

    }

    @PostMapping("/{link}")
    public ResponseEntity<String> generateShortLink(String link) {

        String generatedLink = generateService.generateLink(link);

        return new ResponseEntity<String>(generatedLink, HttpStatus.CREATED);

    }

    @GetMapping("/{generatedId}")
    public RedirectView redirectToGoogle(@PathVariable String generatedId) throws Exception {
        try {
            String originalLink = getLinkService.getLink(generatedId);
            return new RedirectView(originalLink);
        } catch (Exception e) {
            return new RedirectView("/");
        }



    }


}
