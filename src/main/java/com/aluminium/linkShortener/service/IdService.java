package com.aluminium.linkShortener.service;

import org.springframework.stereotype.Service;

@Service
public class IdService {

    public String generateId(Long id) {
            String hashedId = Long.toHexString(id);

            return hashedId;
    }
}
