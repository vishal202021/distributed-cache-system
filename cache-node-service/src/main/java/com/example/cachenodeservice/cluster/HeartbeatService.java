package com.example.cachenodeservice.cluster;

import com.example.cachenodeservice.cache.NodeIdentity;
import com.example.cachenodeservice.dto.HeartbeatRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HeartbeatService {

        private final ClusterClient clusterClient;

        private final NodeIdentity nodeIdentity;

        @Scheduled(fixedRate = 5000)
        public void heartbeat() {


            HeartbeatRequest request =
                    new HeartbeatRequest();

            request.setNodeId(nodeIdentity.getNodeId());

            log.info("Node Health Check");
            clusterClient.heartbeat(request);

    }

}