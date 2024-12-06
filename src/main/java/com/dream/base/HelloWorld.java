package com.dream.base;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

public class HelloWorld {

    public static void main(String[] args) {
        ChatLanguageModel model = OpenAiChatModel.builder()
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();

        String answer = model.generate("你是谁？");
//        String answer = model.generate("你对中国城管怎么看？");
        System.out.println(answer);
    }
}
