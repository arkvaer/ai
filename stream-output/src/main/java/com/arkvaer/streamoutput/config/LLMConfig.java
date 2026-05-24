package com.arkvaer.streamoutput.config;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.arkvaer.common.constants.AIModelEnum;
import com.arkvaer.common.constants.ApiKeysConstant;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author zhangchengtao
 * @date 2026/5/17 10:20
 */
@Configuration
public class LLMConfig {
    @Bean
    public DashScopeApi dashScopeApi() {
        return DashScopeApi
                .builder()
                .apiKey(ApiKeysConstant.API_KEY).build();
    }

    @Bean(name = "deepSeekChatModel")
    public ChatModel DeepSeekChatModel() {
        return DashScopeChatModel.builder()
                .dashScopeApi(DashScopeApi.builder()
                        .apiKey(ApiKeysConstant.API_KEY)
                        .build())
                .defaultOptions(DashScopeChatOptions.builder().model(AIModelEnum.DEEP_SEEK_V4_PRO.getKey()).build())
                .build();
    }

    @Bean(name = "qwenChatModel")
    public ChatModel QWenChatModel() {
        return DashScopeChatModel.builder()
                .dashScopeApi(DashScopeApi.builder()
                        .apiKey(ApiKeysConstant.API_KEY)
                        .build())
                .defaultOptions(DashScopeChatOptions.builder().model(AIModelEnum.QWEN_PLUS.getKey()).build())
                .build();
    }

    @Bean(name = "deepSeekChatClient")
    public ChatClient DeepSeekChatClient(@Qualifier("deepSeekChatModel") ChatModel deepSeekChatModel) {
        return ChatClient.builder(deepSeekChatModel)
                .defaultOptions(ChatOptions.builder().model(AIModelEnum.DEEP_SEEK_V4_FLASH.getKey()).build())
                .build();
    }

    @Bean(name = "qwenChatClient")
    public ChatClient QWenChatClient(@Qualifier("qwenChatModel") ChatModel qwenChatModel) {
        return ChatClient.builder(qwenChatModel)
                .defaultOptions(ChatOptions.builder().model(AIModelEnum.QWEN_PLUS.getKey()).build())
                .build();
    }
}
