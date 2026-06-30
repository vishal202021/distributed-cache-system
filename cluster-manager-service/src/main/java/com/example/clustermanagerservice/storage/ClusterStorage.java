package com.example.clustermanagerservice.storage;
import com.example.clustermanagerservice.model.NodeInfo;

import java.util.Collection;
import java.util.Optional;

public interface ClusterStorage {

    void save(NodeInfo node);

    Optional<NodeInfo> findById(String nodeId);

    Collection<NodeInfo> findAll();

    void delete(String nodeId);

}