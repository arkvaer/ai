package com.arkvaer.ollama.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 *
 * @author arkvaer
 * @date 2026/4/27 16:55
 */
@RestController
public class OllamaController {

    private final ChatModel chatModel;

    public OllamaController(@Qualifier("ollamaChatModel") ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/chat/stream")
    public Flux<String> chat(@RequestParam(value = "msg", defaultValue = "你是谁?") String msg) {
        return chatModel.stream(msg);
    }

}
