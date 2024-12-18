package com.dream.demo;

import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.aggregator.ContentAggregator;
import dev.langchain4j.rag.content.aggregator.DefaultContentAggregator;
import dev.langchain4j.rag.query.Query;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author huzejun
 * @Date 2024-12-18 23:00
 **/
public class QueryDemo {
    public static void main(String[] args) {
        Query query1 = Query.from("1");
        Query query2 = Query.from("2");
        Query query3 = Query.from("3");
        Map<Query, Collection<List<Content>>> queryToContents = Map.of(query1,List.of(List.of(Content.from("1"),Content.from("2"),Content.from("3")))
                ,query2,List.of(List.of(Content.from("3"),Content.from("4"),Content.from("5")))
                ,query3,List.of(List.of(Content.from("3"),Content.from("5"),Content.from("7")))

        );

        ContentAggregator contentAggregator = new DefaultContentAggregator();

        List<Content> aggregate = contentAggregator.aggregate(queryToContents);
        for (Content content : aggregate) {
            System.out.println(content);
        }
    }
}
