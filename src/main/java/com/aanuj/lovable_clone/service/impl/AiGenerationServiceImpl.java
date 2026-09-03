package com.aanuj.lovable_clone.service.impl;

import com.aanuj.lovable_clone.service.AiGenerationService;
import reactor.core.publisher.Flux;

public class AiGenerationServiceImpl implements AiGenerationService {
    @Override
    public Flux<String> streamResponse(String message, Long aLong) {
        return null;
    }
}
