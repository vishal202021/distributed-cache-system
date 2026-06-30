package com.example.cachenodeservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CacheRequest {

    @NotBlank
    private String key;

    @NotNull
    private Object value;

    private long ttl;
}
