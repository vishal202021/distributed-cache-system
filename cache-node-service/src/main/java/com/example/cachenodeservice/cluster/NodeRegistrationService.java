package com.example.cachenodeservice.cluster;

import com.example.cachenodeservice.cache.NodeIdentity;
import com.example.cachenodeservice.dto.RegisterNodeRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NodeRegistrationService {

    private final NodeIdentity nodeIdentity;

    private final ClusterClient clusterClient;

    public void register() {

        log.info("Registering node {}", nodeIdentity.getNodeId());
        RegisterNodeRequest request =
                RegisterNodeRequest.builder()
                        .nodeId(nodeIdentity.getNodeId())
                        .host(nodeIdentity.getHost())
                        .port(nodeIdentity.getPort())
                        .build();

        clusterClient.register(request);
    }
}