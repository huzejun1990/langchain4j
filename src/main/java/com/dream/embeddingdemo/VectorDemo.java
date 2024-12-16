package com.dream.embeddingdemo;

import com.dream.tools.ModelUtil;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.redis.RedisEmbeddingStore;

import java.util.List;

/**
 * @Author huzejun
 * @Date 2024-12-15 17:50
 **/
public class VectorDemo {

    public static void main(String[] args) {

        EmbeddingModel embeddingModel = OpenAiEmbeddingModel.builder()
                .apiKey(ModelUtil.API_KEY_OPENAI)
                .baseUrl(ModelUtil.BASE_URI_OPENAI)
                .build();

//        Response<Embedding> embed = embeddingModel.embed("你好，我是楼兰");
//        System.out.println(embed.content().toString());
//        System.out.println(embed.content().vector().length);

        //        "你好，我是楼兰"
        // "我的名字叫楼兰"
        RedisEmbeddingStore embeddingStore = RedisEmbeddingStore.builder()
                .host("127.0.0.1")
                .port(6379)
                .dimension(1536)
                .build();
//        embeddingStore.add(embed.content());


        List<EmbeddingMatch<TextSegment>> matches = embeddingStore.findRelevant(embeddingModel.embed("今天天气不错").content(), 3, -1);
        for (EmbeddingMatch<TextSegment> match : matches) {
            System.out.println(match.score());
        }
    }
}
