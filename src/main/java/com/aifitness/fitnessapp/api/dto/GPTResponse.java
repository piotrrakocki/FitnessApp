package com.aifitness.fitnessapp.api.dto;

import java.util.List;

public record GPTResponse(
        List<Choice> choices
) {
}
