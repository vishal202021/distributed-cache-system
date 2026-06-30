package com.example.cachenodeservice.storage;
import com.example.cachenodeservice.entity.CacheEntry;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryCacheStorage implements CacheStorage {

    private final ConcurrentHashMap<String, CacheEntry> cache =
            new ConcurrentHashMap<>();

    @Override
    public void put(CacheEntry entry) {
        cache.put(entry.getKey(), entry);
    }

    @Override
    public Optional<CacheEntry> get(String key) {
        return Optional.ofNullable(cache.get(key));
    }

    @Override
    public void delete(String key) {
        cache.remove(key);
    }

    @Override
    public boolean contains(String key) {
        return cache.containsKey(key);
    }

    @Override
    public long size() {
        return cache.size();
    }

    @Override
    public List<CacheEntry> getAll() {
        return new ArrayList<>(cache.values());
    }

}
