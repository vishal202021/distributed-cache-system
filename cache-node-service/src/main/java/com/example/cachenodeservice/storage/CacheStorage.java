package com.example.cachenodeservice.storage;

import com.example.cachenodeservice.entity.CacheEntry;

import java.util.List;
import java.util.Optional;

public interface CacheStorage {

    void put(CacheEntry entry);
    Optional<CacheEntry> get(String key);
    void delete(String key);
    boolean contains(String key);
    long size();
    List<CacheEntry> getAll();

}
