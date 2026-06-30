package com.example.clustermanagerservice.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HeartbeatRequest {

    @NotBlank
    private String nodeId;

}