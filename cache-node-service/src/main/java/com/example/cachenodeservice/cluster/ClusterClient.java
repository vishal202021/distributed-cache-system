package com.example.cachenodeservice.cluster;

import com.example.cachenodeservice.dto.HeartbeatRequest;
import com.example.cachenodeservice.dto.OwnerNodeResponse;
import com.example.cachenodeservice.dto.RegisterNodeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class ClusterClient {

    private final RestClient restClient;

    @Value("${cluster.manager.url}")
    private String clusterManagerUrl;

    public void register(RegisterNodeRequest request) {

        restClient.post()
                .uri(clusterManagerUrl + "/cluster/register")
                .body(request)
                .retrieve()
                .toBodilessEntity();

    }

    public void heartbeat(HeartbeatRequest request) {

        restClient.post()
                .uri(clusterManagerUrl + "/cluster/heartbeat")
                .body(request)
                .retrieve()
                .toBodilessEntity();

    }

    public OwnerNodeResponse getOwner(String key) {

        return restClient.get()
                .uri(clusterManagerUrl + "/cluster/owner?key={key}", key)
                .retrieve()
                .body(OwnerNodeResponse.class);

    }

}