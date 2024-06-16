package org.vagabond.chatbot.payload;

public record ChatRequest(String user, String conversationUuid, String message) {

}
