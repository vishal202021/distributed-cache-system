package com.example.cachenodeservice.eviction;

import com.example.cachenodeservice.entity.CacheEntry;
import com.example.cachenodeservice.metrics.CacheMetrics;
import com.example.cachenodeservice.storage.CacheStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LRUEvictionPolicy implements EvictionPolicy {

    private final CacheStorage storage;
    private final CacheMetrics metrics;

    @Value("${cache.max-size:3}")
    private int maxSize;

    @Override
    public void evict() {

        if (storage.size() < maxSize) {
            return;
        }

        CacheEntry lru = storage.getAll()
                .stream()
                .min((a, b) -> Long.compare(
                        a.getLastAccessTime(),
                        b.getLastAccessTime()))
                .orElse(null);

        if (lru != null) {

            storage.delete(lru.getKey());
            metrics.evicted();

            System.out.println("Evicted : " + lru.getKey());

        }

    }

}