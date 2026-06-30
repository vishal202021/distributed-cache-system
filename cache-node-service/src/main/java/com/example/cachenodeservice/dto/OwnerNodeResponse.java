package com.example.cachenodeservice.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerNodeResponse {

    private String nodeId;
    private String host;
    private int port;

}
