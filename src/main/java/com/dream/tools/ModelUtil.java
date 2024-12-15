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

    public static final String API_KEY_OPENAI="sk-DbMfA18yhwgHLHPx32F4（aslasfdas8sfaas）8c03E1C44416B0B81d3096F1297e";
    // sk-DbMfA18yhwgHLHPx（aslasfdas8sfaas）32F48c03E1C44416B0B81d3096F1297e
    // sk-Qxt1e040220a75c18e3a2193（aslasfdas8sfaas）f6b5cb0d8fb7ff3uxxI1
//    public static final String API_KEY_OPENAI="sk-Qxt1e0402（aslasfdas8sfaas）20a75c18e3a2193f6b5cb0d8fb7ff3uxxI1"; //other user
    public static final String BASE_URI_OPENAI="https://api.xt（aslasfdas8sfaasy.app/v1";   //https://api.（aslasfdas8sfaasxty.app
    // https://api.gp（aslasfdas8sfaastsapi.net/v1;
//    public static final String BASE_URI_OPENAI="https://api.gp（aslasfdas8sfaastsapi.net/v1";
    public static ChatLanguageModel getOpenAIModel(){
        return OpenAiChatModel.builder()
//                .apiKey(API_KEY_OPENAI)
//                .baseUrl(BASE_URI_OPENAI)
                .apiKey("demo")
                .modelName(OpenAiChatModelName.GPT_4_O_MINI)
                .build();
    }

}
