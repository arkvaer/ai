package com.arkvaer.chatmodelvschatclient.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zhangchengtao
 * @date 2026/5/16 23:32
 */
@RestController
public class ChatModelController {

    private final ChatClient dashScopeChatClient;

    public ChatModelController(ChatModel dashScopeChatClient) {
        this.dashScopeChatClient = ChatClient.builder(dashScopeChatClient).build();
    }

    @GetMapping("/chatmodel/dochat")
    public String doChat(@RequestParam(name = "msg", defaultValue = "你是谁?") String msg) {
        return dashScopeChatClient.prompt().user(msg).call().content();
    }
}
