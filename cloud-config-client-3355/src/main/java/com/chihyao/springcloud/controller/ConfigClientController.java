package com.chihyao.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigClientController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    public ConfigClientController() {
    }

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return "serverProt: " + serverPort + "\nconfigInfo: " + configInfo;
    }

}
