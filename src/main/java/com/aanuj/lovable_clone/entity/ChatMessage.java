package com.aanuj.lovable_clone.entity;


import com.aanuj.lovable_clone.enums.MessageRole;

import java.time.Instant;

public class ChatMessage {
    Long id;
    ChatSession chatSession;

    MessageRole role;
    String content;

    String toolCalls;
    Integer tokensUsed;
    Instant createdAt;


}
