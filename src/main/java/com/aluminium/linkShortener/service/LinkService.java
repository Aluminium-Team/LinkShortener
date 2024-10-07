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

        Long id = Long.parseLong(generatedId,16);

        System.out.println(id);

        Optional<Link> link = linkRepository.findById(id);

        if (link.isPresent()) {
            Link linkObject = link.get();

            String originalLink = linkObject.getUrl();

            return originalLink;

        } else {
            throw new Exception("Id not found");
        }

    }

    private void saveLink(String shortnedUrl){

//        ** blocked by "generate id" **

//        Link link = Link.builder(
//                idService.generateId(),
//                shortnedUrl
//                )
//                .build();
//        linkRepository.save(link);
    }

    public String shortenLink(String link) {

        String shortnedLink = generateShortLink(link);
        saveLink(shortnedLink);

        return shortnedLink;

    }

    public String generateShortLink(String originalLink) {
        // Generate a Murmur3 hash and convert it to hex
        String murmurHash = Hashing.murmur3_32().hashString(originalLink, StandardCharsets.UTF_8).toString();
        int hashLength = murmurHash.length();
        // Use the first 6 characters as the short link
        return murmurHash.substring(0, min(hashLength,6));
    }
}
