package com.aluminium.linkShortener.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class IdService {

    @Value("${server.id}")
    private long serverId;

    private final AtomicLong last = new AtomicLong(0L);
    private final AtomicLong requestsPerMs = new AtomicLong(0L);

    public Long generateId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp != last.get()) {
            requestsPerMs.set(1L);  // Reset to 1 if the timestamp has changed
            last.set(timestamp);
        } else {
            requestsPerMs.incrementAndGet();  // Increment if the same timestamp
        }

        // Construct the Snowflake ID using bitwise operations
        return (timestamp << 22) | (serverId << 12) | requestsPerMs.get();
    }

    public String idToHex(Long snowFlakeId) {
        return Long.toHexString(snowFlakeId);
    }

    public Long hexToId(String hex) {
        return Long.parseLong(hex,16);
    }

}
