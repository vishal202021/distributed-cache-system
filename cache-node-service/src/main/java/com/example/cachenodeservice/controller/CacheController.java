package com.example.cachenodeservice.controller;

import com.example.cachenodeservice.dto.CacheRequest;
import com.example.cachenodeservice.dto.CacheResponse;
import com.example.cachenodeservice.service.CacheService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
public class CacheController {

    private final CacheService cacheService;

    @PutMapping
    public void put(@RequestBody @Valid CacheRequest request) {

        cacheService.put(request);

    }

    @GetMapping("/{key}")
    public CacheResponse get(@PathVariable String key) {

        return cacheService.get(key);

    }

    @DeleteMapping("/{key}")
    public void delete(@PathVariable String key) {

        cacheService.delete(key);

    }

}
