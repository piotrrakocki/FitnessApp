package com.aifitness.fitnessapp.api.service;

import com.aifitness.fitnessapp.api.dto.GPTResponse;
import com.aifitness.fitnessapp.api.dto.GptRequest;
import com.aifitness.fitnessapp.api.dto.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GPTServiceImpl implements GPTService {

    private final WebClient webClient;

    private final String apiKey;

    public GPTServiceImpl(WebClient.Builder webClientBuilder,  @Value("${openai.api.key}") String apiKey) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1").build();
        this.apiKey = apiKey;
    }

    @Override
    public Mono<String> getChatResponse(String prompt) {
        GptRequest request = new GptRequest(
                "gpt-4o",
                List.of(new Message("user", prompt))
        );

        return webClient.post()
                .uri("/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(GPTResponse.class)
                .map(response -> response.choices().get(0).message().content());
    }
}
