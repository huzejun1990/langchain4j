package com.dream.aiservicedemo;

import com.dream.tools.ModelUtil;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.agent.tool.ToolSpecifications;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;

import java.time.LocalDateTime;

/**
 * @Author huzejun
 * @Date 2024-12-15 12:57
 **/
public class UserAIService {

    interface Assistant{
        String chat(@MemoryId Long userId, @UserMessage String message);
    }

    @Tool("获取当前日期")
    public static String dataUtil(){
        return LocalDateTime.now().toString();
    }

    public static void main(String[] args) throws NoSuchMethodException {

        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        ChatLanguageModel model = ModelUtil.getOpenAIModel();

        ToolSpecification toolSpecification = ToolSpecifications.toolSpecificationFrom(UserAIService.class.getMethod("dataUtil"));

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
//                .chatMemory(chatMemory)
//                .chatMemoryProvider(memoryId -> chatMemory)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
                .tools(toolSpecification)
                .build();

        System.out.println(assistant.chat(1L, "你好，我是楼兰？"));
        System.out.println(assistant.chat(1L, "我的名字是什么？"));

        System.out.println(assistant.chat(2L, "你好，我是老王"));
        System.out.println(assistant.chat(2L, "我的名字是什么？"));


    }
}
