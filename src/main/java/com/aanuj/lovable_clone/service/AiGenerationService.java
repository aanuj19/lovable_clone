package com.aanuj.lovable_clone.service;

import reactor.core.publisher.Flux;

import java.util.Optional;

public interface AiGenerationService {
    Flux<String> streamResponse(String message, Long aLong);
}
