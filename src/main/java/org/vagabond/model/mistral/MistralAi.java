package org.vagabond.model.mistral;

import jakarta.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.vagabond.model.base.BaseModelAi;

import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.mistralai.MistralAiStreamingChatModel;

@ApplicationScoped
public class MistralAi extends BaseModelAi {

    @ConfigProperty(name = "api.mistral.token")
    private String apiMistralToken;

    public StreamingChatLanguageModel initModel() {
        return MistralAiStreamingChatModel.builder()
                .apiKey(apiMistralToken)
                .modelName("mistral-small")
                .logRequests(true)
                .logResponses(false)
                .maxTokens(2000)
                .build();
    }
}
