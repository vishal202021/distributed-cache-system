package com.example.cachenodeservice.entity;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CacheEntry {

    private String key;

    private Object value;

    private long createdAt;

    private long updatedAt;

    private long ttl;

    private long expireAt;

    private long lastAccessTime;

    private long accessCount;

    private long version;

}
