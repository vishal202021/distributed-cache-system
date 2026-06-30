package com.example.clustermanagerservice.service;

import com.example.clustermanagerservice.dto.OwnerNodeResponse;
import com.example.clustermanagerservice.hashing.HashRing;
import com.example.clustermanagerservice.model.HeartbeatRequest;
import com.example.clustermanagerservice.model.NodeInfo;
import com.example.clustermanagerservice.model.NodeStatus;
import com.example.clustermanagerservice.model.RegisterNodeRequest;
import com.example.clustermanagerservice.storage.ClusterStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClusterServiceImpl implements ClusterService{

    private final ClusterStorage storage;
    private final HashRing hashRing;

    @Override
    public void register(RegisterNodeRequest request) {
        Optional<NodeInfo> existing = storage.findById(request.getNodeId());

        if (existing.isPresent()) {

            NodeInfo node = existing.get();

            node.setStatus(NodeStatus.UP);
            node.setLastHeartbeat(System.currentTimeMillis());
            node.setHost(request.getHost());
            node.setPort(request.getPort());

            storage.save(node);

            hashRing.addNode(node);

            return;
        }

        NodeInfo node=NodeInfo.builder()
                .nodeId(request.getNodeId())
                .host(request.getHost())
                .port(request.getPort())
                .status(NodeStatus.UP)
                .lastHeartbeat(System.currentTimeMillis())
                .build();

        storage.save(node);
        hashRing.addNode(node);
        hashRing.printRing();
    }

    @Override
    public void heartbeat(HeartbeatRequest request) {

        storage.findById(request.getNodeId())
                .ifPresent(node -> {

                    boolean wasDown =
                            node.getStatus() == NodeStatus.DOWN;

                    node.setLastHeartbeat(System.currentTimeMillis());

                    node.setStatus(NodeStatus.UP);

                    storage.save(node);

                    if (wasDown) {

                        hashRing.addNode(node);

                        hashRing.printRing();

                    }


                });
    }

    @Override
    public Collection<NodeInfo> getAllNodes() {
        return storage.findAll();
    }

    @Override
    public void checkNodeHealth() {

        long currentTime = System.currentTimeMillis();

        storage.findAll().forEach(node -> {

            long difference = currentTime - node.getLastHeartbeat();

            if (node.getStatus() == NodeStatus.UP&&difference > 15000) {

                node.setStatus(NodeStatus.DOWN);

                storage.save(node);

                hashRing.removeNode(node);

                hashRing.printRing();

                System.out.println(
                        node.getNodeId() + " marked as DOWN"
                );

            }

        });

    }


    @Override
    public OwnerNodeResponse getOwner(String key) {

            NodeInfo node = hashRing.getNode(key);

            if (node == null) {
                return null;
            }

            return OwnerNodeResponse.builder()
                    .nodeId(node.getNodeId())
                    .host(node.getHost())
                    .port(node.getPort())
                    .build();

        }
    }


