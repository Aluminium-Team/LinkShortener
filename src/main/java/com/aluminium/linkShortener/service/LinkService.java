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

            return originalLink;

        } else {
            throw new Exception("Id not found");
        }

    }

    public String createLink(String url){

        Long generatedId = idService.generateId(1L);

        Link link = linkBuilder(generatedId,url);

        saveLink(link);

        String hexId = idService.idToHex(generatedId);

        return hexId;
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
