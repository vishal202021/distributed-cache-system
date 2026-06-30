package com.example.cachenodeservice.cluster;

import com.example.cachenodeservice.cache.NodeIdentity;
import com.example.cachenodeservice.dto.OwnerNodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoutingService {

    private final OwnerLookupService ownerLookupService;

    private final NodeIdentity nodeIdentity;

    public boolean amIOwner(String key) {

        OwnerNodeResponse owner =
                ownerLookupService.findOwner(key);

        return owner.getNodeId()
                .equals(nodeIdentity.getNodeId());

    }

}