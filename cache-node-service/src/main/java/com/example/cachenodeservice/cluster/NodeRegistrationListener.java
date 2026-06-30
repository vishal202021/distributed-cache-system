package com.example.cachenodeservice.cluster;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NodeRegistrationListener {

    private final NodeRegistrationService registrationService;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {

        registrationService.register();

    }

}