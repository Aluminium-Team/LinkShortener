package com.aluminium.linkShortener.service;

import org.springframework.stereotype.Service;

@Service
public class IdService {

    Long last = 0L;
    Long requestsPerMs = 0L;

    public Long generateId(Long dataCenter) {
        long timestamp = System.currentTimeMillis();

        if (timestamp != last) {
            requestsPerMs = 1L;
            last = timestamp;
        } else {
            requestsPerMs++;
        }

        String snowFlakeId = "" + timestamp + dataCenter + requestsPerMs;

        Long id = Long.valueOf(snowFlakeId);

        return id;
    }

    public String idToHex(Long snowFlakeId) {
        String hex = Long.toHexString(snowFlakeId);

        return hex;
    }

    public Long hexToId(String hex) {
        Long id = Long.parseLong(hex,16);

        return id;
    }

}
