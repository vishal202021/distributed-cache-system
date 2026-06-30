package com.example.clustermanagerservice.hashing;

import com.example.clustermanagerservice.model.NodeInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.NavigableMap;
import java.util.TreeMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class HashRing {

    private final HashFunction hashFunction;
    private final NavigableMap<Long, NodeInfo> ring = new TreeMap<>();

    public void addNode(NodeInfo node) {

        long hash = hashFunction.hash(node.getNodeId());

        ring.put(hash, node);

    }

    public void removeNode(NodeInfo node) {

        long hash = hashFunction.hash(node.getNodeId());

        System.out.println("Removing : " + hash);

        ring.remove(hash);

        System.out.println("Ring Size : " + ring.size());

        printRing();

    }

    public Collection<NodeInfo> getNodes() {

        return ring.values();

    }

    public NodeInfo getNode(String key) {

        if (ring.isEmpty()) {
            return null;
        }

        long hash = hashFunction.hash(key);
        System.out.println(key + " -> " + hash);


        var entry = ring.ceilingEntry(hash);

        if (entry == null) {
            entry = ring.firstEntry();
        }

        System.out.println("Owner : " + entry.getValue().getNodeId());


        return entry.getValue();

    }

    public void printRing() {

        ring.forEach((hash, node) ->

                System.out.println(hash + " -> " + node.getNodeId())

        );

    }

}
