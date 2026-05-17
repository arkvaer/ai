package com.arkvaer.common.constants;

import lombok.Getter;

/**
 *
 * @author zhangchengtao
 * @date 2026/5/17 10:44
 */
@Getter
public enum AIModelEnum {
    DEEP_SEEK_V4_PRO("deepseek-v4-pro", "deepSeek-v4-pro"),
    DEEP_SEEK_V4_FLASH("deepseek-v4-flash", "deepseek-v4-flash"),
    QWEN_PLUS("qwen-plus", "qwen-plus"),
    ;
    private final String key;
    private final String name;


    AIModelEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }
}
