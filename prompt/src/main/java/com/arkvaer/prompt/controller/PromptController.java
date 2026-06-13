package com.arkvaer.prompt.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 *
 * @author zhangchengtao
 * @date 2026/5/17 10:43
 */
@RestController
@RequestMapping("/chat")
public class PromptController {

    private final ChatModel qwenChatModel;
    private final ChatModel deepSeekChatModel;
    private final ChatClient deepSeekChatClient;
    private final ChatClient qwenChatClient;


    public PromptController(@Qualifier(value = "qwenChatModel") ChatModel qwenChatModel, @Qualifier(value = "deepSeekChatModel") ChatModel deepSeekChatModel, @Qualifier(value = "deepSeekChatClient") ChatClient deepSeekChatClient, @Qualifier(value = "qwenChatClient") ChatClient qwenChatClient) {
        this.qwenChatModel = qwenChatModel;
        this.deepSeekChatModel = deepSeekChatModel;
        this.deepSeekChatClient = deepSeekChatClient;
        this.qwenChatClient = qwenChatClient;
    }


    @GetMapping("/deepSeek")
    public Flux<String> deepSeekChatModel(@RequestParam(name = "question", defaultValue = "你是谁") String question) {
        return deepSeekChatModel.stream(question);
    }

    @GetMapping("/qwen")
    public Flux<String> qwenChatModel(@RequestParam(name = "question", defaultValue = "你是谁") String question) {
        return qwenChatModel.stream(question);
    }


    @GetMapping("/deepSeekClient")
    public Flux<String> deepSeekChatClient(@RequestParam(name = "question", defaultValue = "你是谁") String question) {
        return deepSeekChatClient.prompt(question)
                .system("你是一个法律助手,只回答法律问题,其他问题回复: 我只能回答法律问题, 其他问题一律他妈的无可奉告!")
                .user(question)
                .stream().content();
    }

    @GetMapping("/qwenClient")
    public Flux<String> qwenChatClient(@RequestParam(name = "question", defaultValue = "你是谁") String question) {
        return qwenChatClient.prompt(question).stream().content();
    }


}
