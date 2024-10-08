package com.aluminium.linkShortener.controller;

import com.aluminium.linkShortener.service.IdService;
import com.aluminium.linkShortener.service.LinkService;
import com.aluminium.linkShortener.service.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
@RequestMapping("/")
public class GenerateController {

    @Autowired
    LinkService linkService;

    @Autowired
    RequestService requestService;

    @Autowired
    IdService idService;

//    This endpoint is created for testing "not found" error only
    @GetMapping("/")
    public String helloWorld () {

        System.out.println(idService.generateId(5L));
        return "Homepage";

    }


    @PostMapping("/")
    public ResponseEntity<String> generateShortLink(@RequestBody Map<String,String> inputLink, HttpServletRequest request) {

        try{
            String link = inputLink.get("link");

            String domain = requestService.getDomain(request);
            String generatedLink = domain+linkService.createLink(link);

            return new ResponseEntity<String>(generatedLink, HttpStatus.CREATED);

        }catch (IllegalArgumentException e){
            return new ResponseEntity<String>("invalid url", HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("/{hexId}")
    public RedirectView redirectToGoogle(@PathVariable String hexId) throws Exception {
        try {

            String originalLink = linkService.getLink(hexId);
            return new RedirectView(originalLink);

        } catch (Exception e) {
            return new RedirectView("/");
        }


    }

}
