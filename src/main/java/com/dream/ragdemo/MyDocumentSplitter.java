package com.dream.ragdemo;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.segment.TextSegment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author huzejun
 * @Date 2024-12-18 17:33
 **/
public class MyDocumentSplitter implements DocumentSplitter {
    public static final String SPLIT_EXP ="\\s*\\R\\s*\\R\\s*";

    @Override
    public List<TextSegment> split(Document document) {
        List<TextSegment> segments = new ArrayList<>();

        String[] parts = document.text().split(SPLIT_EXP);
        for (String part : parts) {
            segments.add(TextSegment.from(part));
        }
        return segments;
    }
}

