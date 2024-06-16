package org.vagabond;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.vagabond.chatbot.payload.ChatRequest;
import org.vagabond.chatbot.payload.ChatbotResponse;
import org.vagabond.chatbot.service.ChatbotService;

import io.smallrye.mutiny.Multi;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @Inject
    ChatbotService chatbotService;

    @POST()
    @Path("/chat")
    public Multi<ChatbotResponse> chat(ChatRequest chatRequest) {
        var stream = chatbotService.chat(chatRequest.user(), chatRequest.conversationUuid(), chatRequest.message());
        return Multi.createFrom().emitter(emit -> {
            stream
                    .onNext(token -> emit
                            .emit(new ChatbotResponse(token)))
                    .onComplete(response -> {
                        System.out.println(response);
                        emit.emit(
                                new ChatbotResponse(response.content().text(), response.tokenUsage().inputTokenCount(),
                                        response.tokenUsage().outputTokenCount(),
                                        response.tokenUsage().totalTokenCount(),
                                        response.finishReason()));
                        emit.complete();
                    })
                    .onError(emit::fail)
                    .start();
        });
    }
}
