package com.aifitness.fitnessapp.api.dto;

import java.util.List;

public record GptRequest(
        String model,
        List<Message> messages
) {
}
