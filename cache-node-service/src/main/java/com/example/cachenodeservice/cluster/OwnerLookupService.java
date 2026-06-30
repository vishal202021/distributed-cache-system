package com.example.cachenodeservice.cluster;

import com.example.cachenodeservice.dto.OwnerNodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerLookupService {

    private final ClusterClient clusterClient;

    public OwnerNodeResponse findOwner(String key) {

        return clusterClient.getOwner(key);

    }

}