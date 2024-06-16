package org.vagabond.model.base;

import dev.langchain4j.model.chat.StreamingChatLanguageModel;

public abstract class BaseModelAi implements IBaseModelAi {

    public StreamingChatLanguageModel model;

    public abstract StreamingChatLanguageModel initModel();

    public StreamingChatLanguageModel getModel() {
        if (model == null) {
            model = initModel();
        }
        return model;
    }
}
