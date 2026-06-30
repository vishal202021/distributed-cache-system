package com.example.cachenodeservice.cache;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Getter
@Component
public class NodeIdentity {

    private static final String NODE_DIRECTORY = ".cache";
    private static final String NODE_FILE = "node.id";

    private String nodeId;

    private String host;

    private int port;

    private final Environment environment;

    public NodeIdentity(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void initialize() {

        this.nodeId = loadOrCreateNodeId();

        this.host = resolveHost();

        this.port = Integer.parseInt(
                environment.getProperty("server.port", "8080")
        );

    }

    private String loadOrCreateNodeId() {

        try {

            Path directory = Path.of(NODE_DIRECTORY);

            Files.createDirectories(directory);

            Path file = directory.resolve(NODE_FILE);

            if (Files.exists(file)) {

                return Files.readString(file).trim();

            }

            String id = UUID.randomUUID().toString();

            Files.writeString(file, id);

            return id;

        } catch (IOException e) {

            throw new RuntimeException("Unable to create node identity.", e);

        }

    }

    private String resolveHost() {

        try {

            return InetAddress.getLocalHost().getHostAddress();

        } catch (Exception e) {

            return "localhost";

        }

    }

}