package com.dream.base;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.zhipu.ZhipuAiChatModel;

public class HelloWorld {

    public static void main(String[] args) {
        ChatLanguageModel model = OpenAiChatModel.builder()
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();

//        ChatLanguageModel model = ZhipuAiChatModel.builder()
//                .build();

//        String answer = model.generate("你是谁");
////        String answer = model.generate("你对中国城管怎么看");
//        System.out.println(answer);

//        String answer2 = model.generate("请重复一次");
//        System.out.println(answer2);

        UserMessage userMessage = UserMessage.from("你是谁");
        Response<AiMessage> response = model.generate(userMessage);
        AiMessage aiMessage = response.content();
        System.out.println(aiMessage.text());

        UserMessage userMessage2 = UserMessage.from("请重复一次");
        Response<AiMessage> response2 = model.generate(userMessage, aiMessage, userMessage2);

        System.out.println(response2.content().text());
    }
}
