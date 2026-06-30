package com.example.clustermanagerservice.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterNodeRequest {

    @NotBlank
    private String nodeId;

    @NotBlank
    private String host;

    private int port;

}