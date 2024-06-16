package org.vagabond.chatbot.payload;

import dev.langchain4j.model.output.FinishReason;

public record ChatbotResponse(String message,
        Integer inputTokenCount,
        Integer outputTokenCount,
        Integer totalTokenCount, FinishReason finishReason) {

    public ChatbotResponse(String message) {
        this(null, null, null, null, FinishReason.CONTENT_FILTER);
    }
}
