package org.vagabond.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;

public class MemoryStore implements ChatMemoryStore {

    Map<Object, List<ChatMessage>> memory = new HashMap<>();

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        var messages = memory.get(memoryId);
        return messages != null ? messages : new ArrayList<>();
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        memory.put(memoryId, messages);
    }

    @Override
    public void deleteMessages(Object memoryId) {
        memory.remove(memoryId);
    }

}
