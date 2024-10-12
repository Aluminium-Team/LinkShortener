package com.aluminium.linkShortener.service;

import com.aluminium.linkShortener.model.Link;
import com.aluminium.linkShortener.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LinkService {

    @Autowired
    LinkRepository linkRepository;

    @Autowired
    IdService idService;

    public String getLink(String hexId) throws Exception {

        Long id = idService.hexToId(hexId);
        Optional<Link> link = linkRepository.findById(id);

        if (link.isPresent()) {
            Link linkObject = link.get();

            String originalLink = linkObject.getUrl();

            return checkPref(originalLink);

        } else {
            throw new Exception("Id not found");
        }

    }

    private boolean lengthCheck(String url) {
        return url.length() < 500;
    }

    public String createLink(String url) {
        if (!lengthCheck(url)){
            throw new IllegalArgumentException();
        }
        Long generatedId = idService.generateId();

        Link link = linkBuilder(generatedId,url);

        saveLink(link);

        return idService.idToHex(generatedId);
    }

    public String checkPref(String link){
        if (!link.startsWith("http://") && !link.startsWith("https://")) {
            link = "https://" + link;
        }
        return link;
    }

    private void saveLink(Link link) {
        linkRepository.save(link);
    }

    private Link linkBuilder(Long generatedId, String url) {

        return Link.builder(
                    generatedId,
                    url
                    ).build();
    }

}
