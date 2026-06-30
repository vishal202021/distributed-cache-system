package com.example.clustermanagerservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NodeInfo {

    private String nodeId;

    private String host;

    private int port;


    private NodeStatus status;

    private long lastHeartbeat;

}