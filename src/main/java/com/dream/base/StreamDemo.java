package com.dream.base;

import com.dream.tools.ModelUtil;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;

import java.util.concurrent.TimeUnit;

/**
 * @Author huzejun
 * @Date 2024-12-07 3:29
 **/
public class StreamDemo {

    public static void main(String[] args) {
        StreamingChatLanguageModel model = OpenAiStreamingChatModel.builder()
//                .modelName("gpt-4o-mini")
                .baseUrl(ModelUtil.BASE_URI_OPENAI)
                .apiKey(ModelUtil.API_KEY_OPENAI)
                .build();


        model.generate("你好，你是谁", new StreamingResponseHandler<AiMessage>() {
            @Override
            public void onNext(String token) {
                System.out.println(token);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onError(Throwable error) {
                System.out.println(error);
            }


        });
    }
}
