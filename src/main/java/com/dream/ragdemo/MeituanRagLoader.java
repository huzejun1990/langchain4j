package com.dream.ragdemo;

import com.dream.tools.ModelUtil;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.store.embedding.redis.RedisEmbeddingStore;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @Author huzejun
 * @Date 2024-12-18 15:53
 **/
public class MeituanRagLoader {

    public static void main(String[] args) throws URISyntaxException {
        //1.读取本地知识文件
        Path documentPath = Paths.get(MeituanRagLoader.class.getClassLoader().getResource("meituan-questions.txt").toURI());
        DocumentParser documentParser = new TextDocumentParser();
        Document document = FileSystemDocumentLoader.loadDocument(documentPath,documentParser);
        //2、把知识文件分解成一个一个知识条目
        DocumentSplitter splitter = new MyDocumentSplitter();
        List<TextSegment> segments = splitter.split(document);
        //3、对每个条目进行文本向量化
        EmbeddingModel embeddingModel = OpenAiEmbeddingModel.builder()
                .baseUrl(ModelUtil.BASE_URI_OPENAI)
                .apiKey(ModelUtil.API_KEY_OPENAI)
                .build();
        RedisEmbeddingStore embeddingStore = RedisEmbeddingStore.builder()
                .host("127.0.0.1")
                .port(6379)
                .dimension(1536)
                .indexName("meituan-rag")
                .build();

        List<Embedding> embeddings = embeddingModel.embedAll(segments).content();

        embeddingStore.addAll(embeddings,segments);

    }
}
