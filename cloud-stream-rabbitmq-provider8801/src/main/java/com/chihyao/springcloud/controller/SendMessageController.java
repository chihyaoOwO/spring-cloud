package com.chihyao.springcloud.controller;

import com.chihyao.springcloud.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    @Autowired
    private MessageProvider messageProvider;

    @GetMapping("/send")
    public String sendMessage() {
        return messageProvider.send();
    }
}
