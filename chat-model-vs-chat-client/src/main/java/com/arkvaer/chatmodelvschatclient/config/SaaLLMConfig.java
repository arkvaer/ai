package com.arkvaer.chatmodelvschatclient.config;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author arkvaer
 */
@Configuration
public class SaaLLMConfig
{
    @Bean
    public DashScopeApi dashScopeApi()
    {
        return DashScopeApi.builder()
                    .apiKey(System.getenv("AI_DASHSCOPE_API_KEY"))
                .build();
    }


    /**
     * 知识出处：
     * @param dashscopeChatModel  dashscopeChatModel
     * @return ChatClient
     */
    @Bean
    public ChatClient chatClient(ChatModel dashscopeChatModel)
    {
        return ChatClient.builder(dashscopeChatModel).build();
    }
}