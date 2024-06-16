package org.vagabond.model.base;

import dev.langchain4j.model.chat.StreamingChatLanguageModel;

public interface IBaseModelAi {
    StreamingChatLanguageModel getModel();
}
