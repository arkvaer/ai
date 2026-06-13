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
public class StreamOutputController {

    private final ChatModel qwenChatModel;
    private final ChatModel deepSeekChatModel;
    private final ChatClient deepSeekChatClient;
    private final ChatClient qwenChatClient;


    public StreamOutputController(@Qualifier(value = "qwenChatModel") ChatModel qwenChatModel, @Qualifier(value = "deepSeekChatModel") ChatModel deepSeekChatModel, @Qualifier(value = "deepSeekChatClient") ChatClient deepSeekChatClient, @Qualifier(value = "qwenChatClient") ChatClient qwenChatClient) {
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
        return deepSeekChatClient.prompt(question).stream().content();
    }

    @GetMapping("/qwenClient")
    public Flux<String> qwenChatClient(@RequestParam(name = "question", defaultValue = "你是谁") String question) {
        return qwenChatClient.prompt(question).stream().content();
    }


}
