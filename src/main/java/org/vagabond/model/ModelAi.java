package org.vagabond.model;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.vagabond.model.mistral.MistralAi;

import dev.langchain4j.model.chat.StreamingChatLanguageModel;

@ApplicationScoped
public class ModelAi {

    @Inject
    MistralAi mistralAi;

    public StreamingChatLanguageModel getModel() {
        return mistralAi.getModel();
    }

}
