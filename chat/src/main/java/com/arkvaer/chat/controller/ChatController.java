package com.arkvaer.chat.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zhangchengtao
 * @date 2026/4/26 21:46
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Resource //支持自动注入
    private ChatModel dashScopeChatModel;

    /**
     * http://localhost:8003/chatclientv2/dochat
     * @param msg
     * @return
     */
    @GetMapping("/chatclientv2/dochat")
    public String doChat(@RequestParam(name = "msg",defaultValue = "你是谁") String msg)
    {
        String result = dashScopeChatModel.call(msg);
        System.out.println("ChatClient响应：" + result);
        return result;
    }

}
