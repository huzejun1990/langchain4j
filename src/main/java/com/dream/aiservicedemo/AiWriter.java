package com.dream.aiservicedemo;

import com.dream.tools.ModelUtil;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * @Author huzejun
 * @Date 2024-12-13 21:28
 **/
public class AiWriter {

    interface Writer{
        @SystemMessage("你是一个散文作家，根据输入的题目写一篇200字以内的散文")
        String write(String content);
    }

    interface Writer2{
        @SystemMessage("你是一个散文作家，写一篇散文，题目是{{title}}，字数不超过{{count}}个字")
        String write(@UserMessage String message, @V("title") String title, @V("count") Long count);
    }

    public static void main(String[] args) {
        ChatLanguageModel model = ModelUtil.getOpenAIModel();

//        Writer writer = AiServices.create(Writer.class, model);
//        String content = writer.write("我最感谢的人");
//        System.out.println(content);

        Writer2 writer = AiServices.create(Writer2.class, model);
        String content = writer.write("写一篇作文","最爱看的电影",200L);
        System.out.println(content);
    }
}
