package com.example.clustermanagerservice.controller;

import com.example.clustermanagerservice.dto.OwnerNodeResponse;
import com.example.clustermanagerservice.model.HeartbeatRequest;
import com.example.clustermanagerservice.model.NodeInfo;
import com.example.clustermanagerservice.model.RegisterNodeRequest;
import com.example.clustermanagerservice.service.ClusterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/cluster")
@RequiredArgsConstructor
public class ClusterController {

    private final ClusterService clusterService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody @Valid RegisterNodeRequest request) {

        clusterService.register(request);

        return ResponseEntity.ok("Node Registered Successfully");

    }

    @PostMapping("/heartbeat")
    public ResponseEntity<String> heartbeat(
            @RequestBody @Valid HeartbeatRequest request) {

        clusterService.heartbeat(request);

        return ResponseEntity.ok("Heartbeat Received");

    }

    @GetMapping("/nodes")
    public ResponseEntity<Collection<NodeInfo>> nodes() {

        return ResponseEntity.ok(clusterService.getAllNodes());

    }

    @GetMapping("/owner")
    public ResponseEntity<OwnerNodeResponse> getOwner(
            @RequestParam String key) {

        OwnerNodeResponse response = clusterService.getOwner(key);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);

    }

}