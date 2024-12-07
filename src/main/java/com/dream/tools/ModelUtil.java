package com.dream.tools;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;

/**
 * Hello world!
 *
 */
public class ModelUtil
{
    public static final String API_KEY_ZHIPU="2423212";

    public static final String API_KEY_OPENAI="sk-DbMfA18yhwgH（afdaadsfafasfdasdf）LHPx32F48c03E1C44416B0B81d3096F1297e";
    // sk-DbMfA18yhwgHLHPx32(asfdasfxw)F48c03E1C44416B0B81d3096F1297e
    // sk-Qxt1e040220a75c18e3a2(safsafda)193f6b5cb0d8fb7ff3uxxI1
//    public static final String API_KEY_OPENAI="sk-Qxt1e040(sfaasdfas)220a75c18e3a2193f6b5cb0d8fb7ff3uxxI1"; //other user
    public static final String BASE_URI_OPENAI="https://api.x(asfasfdsafasfdas)ty.app/v1";   //https://ap(safasfda)i.xty.app
    // https://api.gp(asdfsa)tsapi.net/v1;
//    public static final String BASE_URI_OPENAI="https://api.g(sdfassf)ptsapi.net/v1";
    public static ChatLanguageModel getOpenAiModel(){
        return OpenAiChatModel.builder()
//                .apiKey(API_KEY_OPENAI)
//                .baseUrl(BASE_URI_OPENAI)
                .apiKey("demo")
                .modelName(OpenAiChatModelName.GPT_4_O_MINI)
                .build();
    }

}
