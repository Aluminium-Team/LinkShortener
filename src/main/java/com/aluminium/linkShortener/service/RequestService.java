package com.aluminium.linkShortener.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    private String convertDomain(String serverName, int serverPort){

        String domain = serverName+":"+serverPort+"/";

        return domain;
    }

    public String getDomain(HttpServletRequest request){
        return convertDomain(
                request.getServerName(),
                request.getServerPort()
        );
    }

}
