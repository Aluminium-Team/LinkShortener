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

    private boolean lengthCheck(String url){
        if (url.length()>=500){
            return false;
        }
        return true;
    }

    public String createLink(String url){
        if (!lengthCheck(url)){
            throw new IllegalArgumentException();
        }
        Long generatedId = idService.generateId(1L);

        Link link = linkBuilder(generatedId,url);

        saveLink(link);

        String hexId = idService.idToHex(generatedId);

        return hexId;
    }

    public String checkPref(String link){
        if (!link.startsWith("http://") && !link.startsWith("https://")) {
            link = "https://" + link;
        }
        return link;
    }

    private void saveLink(Link link){
        linkRepository.save(link);
    }

    private Link linkBuilder(Long generatedId, String url){

        Link link = Link.builder(
                    generatedId,
                    url
                    ).build();

        return  link;
    }

}
