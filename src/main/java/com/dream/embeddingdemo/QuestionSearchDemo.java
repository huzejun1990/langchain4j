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
 * @Date 2024-12-16 16:10
 **/
public class QuestionSearchDemo {
    public static void main(String[] args) {
        EmbeddingModel embeddingModel = OpenAiEmbeddingModel.builder()
                .baseUrl(ModelUtil.BASE_URI_OPENAI)
                .apiKey(ModelUtil.API_KEY_OPENAI)
                .build();

        RedisEmbeddingStore embeddingStore = RedisEmbeddingStore.builder()
                .host("127.0.0.1")
                .port(6379)
                .dimension(1536)
                .indexName("question")
                .build();

        //预设几个指示，生成问题
        TextSegment textSegment1 = TextSegment.textSegment("客服电话是：400-8448448");
        TextSegment textSegment2 = TextSegment.textSegment("客服工作时间是周一到周五");
        TextSegment textSegment3 = TextSegment.textSegment("客服电话是：400-8778778");
        TextSegment textSegment4 = TextSegment.textSegment("我的名字叫楼兰");
        Response<Embedding> embed1 = embeddingModel.embed(textSegment1);
        Response<Embedding> embed2 = embeddingModel.embed(textSegment2);
        Response<Embedding> embed3 = embeddingModel.embed(textSegment3);
        Response<Embedding> embed4 = embeddingModel.embed(textSegment4);
        //存储向量
        embeddingStore.add(embed1.content(),textSegment1);
        embeddingStore.add(embed2.content(),textSegment2);
        embeddingStore.add(embed3.content(),textSegment3);
        embeddingStore.add(embed4.content(),textSegment4);

        // 预设一个问题，生成向量
        Response<Embedding> embed = embeddingModel.embed("客服电话是多少");
        // 查询
        List<EmbeddingMatch<TextSegment>> result = embeddingStore.findRelevant(embed.content(),10,-1);
        for (EmbeddingMatch<TextSegment> embeddingMatch : result) {
            System.out.println(embeddingMatch.embedded().text()+",分数为："+embeddingMatch.score());
        }

    }
}
