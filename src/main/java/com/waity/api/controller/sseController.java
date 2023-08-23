package com.waity.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.waity.api.service.sseService;

@RestController
public class sseController {

    private sseService sseService;

    public sseController(sseService sseService) {
        this.sseService = sseService;
    }
    @GetMapping(value = "/api/sse/channel", params = "key", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(@RequestParam("key") String key) throws Exception {
        SseEmitter emitter = sseService.add(key);
        try {
            emitter.send(SseEmitter.event()
                    .name("connect")
                    .data("connected!"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(emitter);
    }
    @PostMapping(value = "/api/sse/channel/scrape", params = "key")
    public ResponseEntity<Void> scrape(@RequestParam("key") String key) throws Exception {
        sseService.scrapeChannels(key, 10);
        return ResponseEntity.ok().build();
    }
}
