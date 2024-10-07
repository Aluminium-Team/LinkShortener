package com.aluminium.linkShortener.service;

import com.aluminium.linkShortener.model.Link;
import com.aluminium.linkShortener.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetLinkService {

    @Autowired
    LinkRepository linkRepository;

    public String getLink(String generatedId) throws Exception {

        Long id = Long.valueOf(generatedId);

        Optional<Link> link = linkRepository.findById(id);

        if (link.isPresent()) {
            Link linkObject = link.get();

            String originalLink = linkObject.getUrl();

            return originalLink;

        } else {
            throw new Exception("Id not found");
        }

    }
}
