package com.aifitness.fitnessapp.api.service;

import reactor.core.publisher.Mono;

public interface GPTService {

    Mono<String> getChatResponse(String prompt);
}
