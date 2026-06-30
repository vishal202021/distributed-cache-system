package com.example.cachenodeservice.metrics;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
@Getter
public class CacheMetrics {

    private final AtomicLong cacheHits = new AtomicLong();
    private final AtomicLong cacheMisses = new AtomicLong();
    private final AtomicLong cachePuts = new AtomicLong();
    private final AtomicLong cacheDeletes = new AtomicLong();
    private final AtomicLong expiredKeys = new AtomicLong();
    private final AtomicLong evictedKeys = new AtomicLong();

    public void hit() {
        cacheHits.incrementAndGet();
    }

    public void miss() {
        cacheMisses.incrementAndGet();
    }

    public void put() {
        cachePuts.incrementAndGet();
    }

    public void delete() {
        cacheDeletes.incrementAndGet();
    }

    public void expired() {
        expiredKeys.incrementAndGet();
    }

    public void evicted() {
        evictedKeys.incrementAndGet();
    }

}