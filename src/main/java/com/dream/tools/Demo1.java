package com.dream.tools;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

/**
 * @Author huzejun
 * @Date 2024-12-08 1:24
 **/
public class Demo1 {

    public static void main(String[] args) {
        ChatLanguageModel model = OpenAiChatModel.builder()
                .apiKey("demo").modelName("gpt-4o-mini").build();
        System.out.println(model.generate("今天是几月几号？ "));
    }
}
