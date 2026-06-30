package com.example.clustermanagerservice.storage;
import com.example.clustermanagerservice.model.NodeInfo;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryClusterStorage implements ClusterStorage {

    private final ConcurrentHashMap<String, NodeInfo> cluster =
            new ConcurrentHashMap<>();

    @Override
    public void save(NodeInfo node) {
        cluster.put(node.getNodeId(), node);
    }

    @Override
    public Optional<NodeInfo> findById(String nodeId) {
        return Optional.ofNullable(cluster.get(nodeId));
    }

    @Override
    public Collection<NodeInfo> findAll() {
        return cluster.values();
    }

    @Override
    public void delete(String nodeId) {
        cluster.remove(nodeId);
    }

}