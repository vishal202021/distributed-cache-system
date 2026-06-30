package com.example.cachenodeservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CacheResponse {
    private String key;

    private Object value;

    private boolean found;
}
