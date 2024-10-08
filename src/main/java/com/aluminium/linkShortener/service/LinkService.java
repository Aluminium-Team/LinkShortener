package com.aluminium.linkShortener.service;

import com.aluminium.linkShortener.model.Link;
import com.aluminium.linkShortener.repository.LinkRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static java.lang.Math.min;

@Service
public class LinkService {

    @Autowired
    LinkRepository linkRepository;

    @Autowired
    IdService idService;

    public String getLink(String generatedId) throws Exception {

        Long id = idService.hexToId(generatedId);

        Optional<Link> link = linkRepository.findById(id);

        if (link.isPresent()) {
            Link linkObject = link.get();

            String originalLink = linkObject.getUrl();

            return originalLink;

        } else {
            throw new Exception("Id not found");
        }

    }

    public String createLink(String shortnedUrl){

//        ** blocked by "generate id" **

//        Link link = Link.builder(
//                idService.generateId(),
//                shortnedUrl
//                )
//                .build();
//        linkRepository.save(link);
        return "Do your Magic 3am Ramez";
    }

}
