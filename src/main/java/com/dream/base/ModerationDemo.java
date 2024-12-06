package com.dream.base;

import dev.langchain4j.model.moderation.Moderation;
import dev.langchain4j.model.moderation.ModerationModel;
import dev.langchain4j.model.openai.OpenAiModerationModel;
import dev.langchain4j.model.output.Response;

/**
 * @Author huzejun
 * @Date 2024-12-07 3:53
 **/
public class ModerationDemo {
    public static void main(String[] args) {
        ModerationModel model = OpenAiModerationModel.builder()
                .apiKey("demo")
                .build();
        Response<Moderation> response = model.moderate("杀人偿命，欠债还钱"); //敏感词过滤
        System.out.println(response.content().flaggedText());
    }
}
