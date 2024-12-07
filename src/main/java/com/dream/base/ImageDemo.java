package com.dream.base;

import com.dream.tools.ModelUtil;


import dev.langchain4j.data.image.Image;

import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.output.Response;

/**
 * @Author huzejun
 * @Date 2024-12-07 4:00
 **/
public class ImageDemo {
    public static void main(String[] args) {
//        ImageModel model = OpenAiImageModel.builder()
//                .baseUrl(ModelUtil.BASE_URI_OPENAI)
//                .apiKey(ModelUtil.API_KEY_OPENAI)
//                .build();

        ImageModel model = OpenAiImageModel.builder()
                .baseUrl(ModelUtil.BASE_URI_OPENAI)
                .apiKey(ModelUtil.API_KEY_OPENAI)
                .build();

        Response<Image> response = model.generate("赛博朋克风格的汽车");
        System.out.println(response.content().url());
        // {"error":{"message":"当前分组 default 下对于模型 dall-e-3 无可用渠道

    }
}
