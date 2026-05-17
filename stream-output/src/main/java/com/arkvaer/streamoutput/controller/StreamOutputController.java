package com.arkvaer.streamoutput.controller;

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

    public StreamOutputController(@Qualifier(value = "qwenChatModel") ChatModel qwenChatModel, @Qualifier(value = "deepSeekChatModel") ChatModel deepSeekChatModel) {
        this.qwenChatModel = qwenChatModel;
        this.deepSeekChatModel = deepSeekChatModel;
    }


    @GetMapping("/deepSeek")
    public Flux<String> deepSeekChatModel(@RequestParam(name = "question") String question) {
        return deepSeekChatModel.stream(question);
    }

    @GetMapping("/qwen")
    public Flux<String> qwenChatModel(@RequestParam(name = "question") String question) {
        return qwenChatModel.stream(question);
    }

}
