package com.example.clustermanagerservice.hashing;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class SHA256HashFunction implements HashFunction {

    @Override
    public long hash(String value) {

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(
                    value.getBytes(StandardCharsets.UTF_8)
            );

            return ByteBuffer.wrap(hash).getLong() & Long.MAX_VALUE;

        } catch (Exception e) {

            throw new RuntimeException("Unable to hash value", e);

        }

    }

}