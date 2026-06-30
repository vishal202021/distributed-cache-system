package com.example.cachenodeservice.controller;

import com.example.cachenodeservice.metrics.CacheMetrics;
import com.example.cachenodeservice.storage.CacheStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MetricsController {

    private final CacheMetrics metrics;
    private final CacheStorage storage;

    @GetMapping("/metrics/cache")
    public ResponseEntity<Map<String, Object>> metrics() {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("Cache Size", storage.size());
        map.put("Cache Hits", metrics.getCacheHits().get());
        map.put("Cache Misses", metrics.getCacheMisses().get());
        map.put("Cache Puts", metrics.getCachePuts().get());
        map.put("Cache Deletes", metrics.getCacheDeletes().get());
        map.put("Expired Keys", metrics.getExpiredKeys().get());
        map.put("Evicted Keys", metrics.getEvictedKeys().get());

        return ResponseEntity.ok(map);

    }

}