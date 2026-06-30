package com.example.cachenodeservice.service;

import com.example.cachenodeservice.dto.CacheRequest;
import com.example.cachenodeservice.dto.CacheResponse;

public interface CacheService {

    void put(CacheRequest request);

    CacheResponse get(String key);

    void delete(String key);

}