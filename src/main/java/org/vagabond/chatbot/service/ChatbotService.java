package org.vagabond.chatbot.service;

import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.vagabond.memory.MemoryStore;
import org.vagabond.model.ModelAi;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;

@ApplicationScoped
public class ChatbotService {

    @Inject
    ModelAi modelAi;

    Map<String, Assistant> assistants = new HashMap<>();
    MemoryStore memoryStore = new MemoryStore();

    public Assistant geAssistant(String key) {
        var assistant = assistants.get(key);
        if (assistant == null) {
            ChatMemoryProvider memoryStoreProvider = memoryId -> MessageWindowChatMemory.builder()
                    .id(memoryId)
                    .maxMessages(10)
                    .chatMemoryStore(memoryStore)
                    .build();
            assistant = AiServices.builder(Assistant.class)
                    .streamingChatLanguageModel(modelAi.getModel())
                    .chatMemoryProvider(memoryStoreProvider)
                    .build();
            assistants.put(key, assistant);
        }
        return assistant;
    }

    public TokenStream chat(String user, String conversationUuid, String message) {
        var answer = geAssistant(user).chat(conversationUuid, message);
        return answer;
    }

}
