package com.waity.api.service;

import com.waity.api.dto.channelDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class sseService {
    private ConcurrentHashMap<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    @Autowired
    youtubeDataApiService youtubeDataApiService;
    @Autowired
    scrapeService scrapeService;
    @Autowired
    channelService channelService;

    public SseEmitter add(String key) throws Exception {
        SseEmitter emitter = new SseEmitter(1L * 60 * 1000);
        this.emitters.put(key, emitter);
        log.info("new emitter added: {}", emitter);
        log.info("emitter set size: {}", emitters.size());
        // 만료되면 리스트에서 삭제
        emitter.onCompletion(() -> {
            log.info("onCompletion callback");
            this.emitters.remove(key);
        });
        emitter.onTimeout(() -> {
            log.info("onTimeout callback");
            emitter.complete();
        });

        return emitter;
    }
    public void scrapeChannels(String key, int maxResults) throws Exception {
        SseEmitter emitter = emitters.get(key);
        List<String> channelIds = youtubeDataApiService.channelList(maxResults);
        for(int i = 0; i < channelIds.size(); i++) {
            String channelId = channelIds.get(i);
            channelDTO channel = scrapeService.scrapeChannel(channelId);
            try {
                channelService.insertChannel(channel);
                emitter.send(SseEmitter.event()
                        .name("success")
                        .data(channel));
            } catch(Exception e) {
                emitter.send(SseEmitter.event()
                        .name("fail")
                        .data(channel));
                log.info(e.getStackTrace().toString());
            }
        }
        emitter.send(SseEmitter.event()
                .name("end")
                .data("end"));
        emitter.complete();
    }
}
