package xyz.greatinvincible.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    private static String CHANNEL = "budai";

    private RedisTemplate<String, String> redisTemplate;

    public RedisController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/publish")
    public void publish(@RequestParam String message) {
        // 发送消息
        redisTemplate.convertAndSend(CHANNEL, message);
    }
}
