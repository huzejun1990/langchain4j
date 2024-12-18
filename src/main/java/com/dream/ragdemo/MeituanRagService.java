package com.dream.ragdemo;

import com.dream.tools.ModelUtil;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.content.injector.ContentInjector;
import dev.langchain4j.rag.content.injector.DefaultContentInjector;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.redis.RedisEmbeddingStore;

import java.time.LocalDateTime;

/**
 * @Author huzejun
 * @Date 2024-12-18 21:39
 **/
public class MeituanRagService {

    //增强接口
    interface AiCustomer {
        String answer(String question);
    }

    public static AiCustomer create() {
        ChatLanguageModel chatLanguageModel = ModelUtil.getOpenAIModel();

        EmbeddingModel embeddingModel = OpenAiEmbeddingModel.builder()
                .baseUrl(ModelUtil.BASE_URI_OPENAI)
                .apiKey(ModelUtil.API_KEY_OPENAI)
                .build();

        EmbeddingStore<TextSegment> embeddingStore = RedisEmbeddingStore.builder()
                .host("127.0.0.1")
                .port(6379)
                .dimension(1536)
                .indexName("meituan-rag")
                .build();

        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)     //向量存储模型
                .embeddingModel(embeddingModel)     //向量模型
                .maxResults(5)      //最相似的5个结果
                .minScore(0.8)      //只找相似度在0.8以上的内容
                .build();

        ContentInjector contentInjector = new DefaultContentInjector();

        DefaultRetrievalAugmentor retrievalAugmentor = DefaultRetrievalAugmentor.builder()
                .contentRetriever(contentRetriever)
                .contentInjector(contentInjector)
                .build();

        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        return AiServices.builder(AiCustomer.class)
                .chatLanguageModel(chatLanguageModel)
                .retrievalAugmentor(retrievalAugmentor)
                .tools(new DataCalculator())
                .chatMemory(chatMemory)
                .build();
    }
    //工具类
    static class DataCalculator {

        @Tool("计算指定天数后的具体日期")
        String date(Integer days){
            return LocalDateTime.now().plusDays(days).toString();
        }
    }

    public static void main(String[] args) {
        //获得代理的服务对象
        AiCustomer aiCustomer = MeituanRagService.create();
        //通过代理服务对象调用大模型
        String result = aiCustomer.answer("今天的余额提现，最晚哪天能到账？给出具体日期");
        System.out.println(result);
    }
}
