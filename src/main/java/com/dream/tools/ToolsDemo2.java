package com.dream.tools;

import dev.langchain4j.agent.tool.*;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ToolExecutionResultMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.tool.DefaultToolExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author huzejun
 * @Date 2024-12-08 2:32
 **/
public class ToolsDemo2 {

    public static void main(String[] args) {

        ChatLanguageModel model = ModelUtil.getOpenAIModel();
        //构建工具
        List<ToolSpecification> toolSpecifications = ToolSpecifications.toolSpecificationsFrom(WeatherUtil.class);

        //构建对话消息
        List<ChatMessage> chatMessages = new ArrayList<>();

        UserMessage userMessage1 = UserMessage.from("北京市的天气怎么样?");
        chatMessages.add(userMessage1);
        //第一次与AI大模型交互，获得需要调用的工具
        Response<AiMessage> aiMessageResponse = model.generate(chatMessages, toolSpecifications);

        AiMessage aiMessage = aiMessageResponse.content();
        System.out.println(aiMessage);
        List<ToolExecutionRequest> toolExecutionRequests = aiMessage.toolExecutionRequests();

        //本地执语句的方法
        if (aiMessage.hasToolExecutionRequests()) {
            for (ToolExecutionRequest toolExecutionRequest : aiMessage.toolExecutionRequests()) {
                String methodName = toolExecutionRequest.name();
                String arguments = toolExecutionRequest.arguments();

                System.out.println("调用的方法："+methodName);
                System.out.println("调用的参数："+arguments);

            }
        }
        chatMessages.add(aiMessage);

        WeatherUtil weatherUtil = new WeatherUtil();
        //将工具调用的方法与聊天消息一起传给AI大模式
        toolExecutionRequests.forEach(toolExecutionRequest -> {
            DefaultToolExecutor toolExecutor = new DefaultToolExecutor(weatherUtil,toolExecutionRequest);
            String result = toolExecutor.execute(toolExecutionRequest, UUID.randomUUID().toString());
            System.out.println("工具执行结果：" + result);
            ToolExecutionResultMessage toolExecutionResultMessage = ToolExecutionResultMessage.from(toolExecutionRequest, result);
            chatMessages.add(toolExecutionResultMessage);
        });
        //拿到本地工具执行的最终结果
        AiMessage finalResponse = model.generate(chatMessages).content();
        System.out.println("最终的结果："+finalResponse.text());

    }

    static class WeatherUtil{
        @Tool("获取某一个具体城市的天气")
        public static String getWeather(@P("指定的城市") String city) {
            return "今天"+city+"天气晴朗";
        }
    }
}
