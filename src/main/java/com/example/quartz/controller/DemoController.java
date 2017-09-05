package com.example.quartz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/9/5.
 */
@RestController
public class DemoController {

    @GetMapping("a")
    public Mono<String> demo(){
        return Mono.justOrEmpty("ok----->handle");
    }

}
