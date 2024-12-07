package com.dream.chatmemory;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

/**
 * @Author huzejun
 * @Date 2024-12-07 20:50
 **/
public class FriendExample {
    public static void main(String[] args) {
        ChatLanguageModel model = OpenAiChatModel.builder()
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();

        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .maxMessages(10)
                .build();

        chatMemory.add(UserMessage.from("你好，我是小明，你是我最好的兄弟，叫做大强"));
        AiMessage answer = model.generate(chatMemory.messages()).content();
        System.out.println(answer.text());
        chatMemory.add(answer);

        chatMemory.add(UserMessage.from("小明最好的朋友是谁？"));
        AiMessage answer2 = model.generate(chatMemory.messages()).content();
        System.out.println(answer2.text());
        chatMemory.add(answer2);

        UserMessage.from("小明最好的朋友是谁？");
    }
}
