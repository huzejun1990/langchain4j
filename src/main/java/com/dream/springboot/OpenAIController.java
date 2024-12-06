package com.dream.springboot;

import dev.langchain4j.model.chat.ChatLanguageModel;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author huzejun
 * @Date 2024-12-07 5:12
 **/
@RestController
public class OpenAIController {

    @Resource
    private ChatLanguageModel chatLanguageModel;

    @GetMapping("/openai/hello")
    public String hello(){
        return chatLanguageModel.generate("你好,你是谁");
    }
}
