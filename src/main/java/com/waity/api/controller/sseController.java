package com.waity.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.waity.api.service.sseService;

import java.io.IOException;

@RestController
public class sseController {

    @Autowired
    sseService sseService;

    @GetMapping(value = "/api/sse/channel", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect() {
        SseEmitter emitter = new SseEmitter();
        sseService.add(emitter);
        try {
            emitter.send(SseEmitter.event()
                    .name("connect")
                    .data("connected!"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(emitter);
    }
    @PostMapping(value = "/api/sse/channel/scrape")
    public ResponseEntity<Void> scrape() throws Exception {
        sseService.scrapeChannels(30);
        return ResponseEntity.ok().build();
    }
}
