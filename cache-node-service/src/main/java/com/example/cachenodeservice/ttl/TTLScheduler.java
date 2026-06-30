package com.example.cachenodeservice.ttl;
import com.example.cachenodeservice.metrics.CacheMetrics;
import com.example.cachenodeservice.storage.CacheStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TTLScheduler {

    private final CacheStorage storage;
    private final CacheMetrics metrics;

    @Scheduled(fixedRate = 5000)
    public void removeExpiredKeys() {

        long now = System.currentTimeMillis();
        System.out.println(storage.getAll().size());

        storage.getAll().forEach(entry -> {

            if (entry.getExpireAt() <= now) {

                storage.delete(entry.getKey());
                metrics.expired();

                log.info("Expired key removed : {}", entry.getKey());


            }
            log.info("TTL Remaining: {} ms",
                    entry.getExpireAt() - System.currentTimeMillis());

        });
    }



}