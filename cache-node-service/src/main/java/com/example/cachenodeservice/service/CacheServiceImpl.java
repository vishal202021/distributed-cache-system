package com.example.cachenodeservice.service;

import com.example.cachenodeservice.dto.CacheRequest;
import com.example.cachenodeservice.dto.CacheResponse;
import com.example.cachenodeservice.entity.CacheEntry;
import com.example.cachenodeservice.eviction.EvictionPolicy;
import com.example.cachenodeservice.metrics.CacheMetrics;
import com.example.cachenodeservice.storage.CacheStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

    private final CacheStorage storage;
    private final EvictionPolicy evictionPolicy;
    private final CacheMetrics metrics;

    @Override
    public void put(CacheRequest request) {


        evictionPolicy.evict();
        metrics.put();

        long now = System.currentTimeMillis();
        long expireAt = request.getTtl() <= 0
                ? Long.MAX_VALUE
                : now + TimeUnit.SECONDS.toMillis(request.getTtl());

        System.out.println("Now       : " + now);
        System.out.println("Expire At : " + expireAt);
        System.out.println("TTL from request = " + request.getTtl());
        CacheEntry entry = CacheEntry.builder()
                .key(request.getKey())
                .value(request.getValue())
                .createdAt(now)
                .updatedAt(now)
                .ttl(request.getTtl())
                .expireAt(expireAt)
                .lastAccessTime(now)
                .accessCount(0)
                .version(1)
                .build();

        storage.put(entry);
    }

    @Override
    public CacheResponse get(String key) {

        return storage.get(key)
                .map(entry -> {

                    if (entry.getExpireAt() < System.currentTimeMillis()) {
                        storage.delete(key);
                        metrics.miss();
                        return CacheResponse.builder().found(false).build();
                    }

                    entry.setLastAccessTime(System.currentTimeMillis());
                    entry.setAccessCount(entry.getAccessCount() + 1);
                    metrics.hit();

                    return CacheResponse.builder()
                            .key(entry.getKey())
                            .value(entry.getValue())
                            .found(true)
                            .build();

                }).orElseGet(() -> {
                    metrics.miss();
                    return CacheResponse.builder()
                            .found(false)
                            .build();
                });
    }

    @Override
    public void delete(String key) {

        metrics.delete();
        storage.delete(key);

    }
}