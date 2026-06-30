package com.example.clustermanagerservice.service;

import com.example.clustermanagerservice.dto.OwnerNodeResponse;
import com.example.clustermanagerservice.model.HeartbeatRequest;
import com.example.clustermanagerservice.model.NodeInfo;
import com.example.clustermanagerservice.model.RegisterNodeRequest;

import java.util.Collection;

public interface ClusterService {

    void register(RegisterNodeRequest request);

    void heartbeat(HeartbeatRequest request);

    Collection<NodeInfo> getAllNodes();

    void checkNodeHealth();

    OwnerNodeResponse getOwner(String key);

}
