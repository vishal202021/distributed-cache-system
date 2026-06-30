package com.example.clustermanagerservice.scheduler;


import com.example.clustermanagerservice.service.ClusterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NodeHealthScheduler {

    private final ClusterService clusterService;

    @Scheduled(fixedRate = 5000)
    public void monitorNodes() {

        clusterService.checkNodeHealth();

    }

}