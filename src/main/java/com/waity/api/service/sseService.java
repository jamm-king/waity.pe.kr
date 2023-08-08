package com.waity.api.service;

import com.waity.api.dto.channelDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class sseService {
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    @Autowired
    youtubeDataApiService youtubeDataApiService;
    @Autowired
    scrapeService scrapeService;
    @Autowired
    channelService channelService;

    public SseEmitter add(SseEmitter emitter) {
        this.emitters.add(emitter);
        log.info("new emitter added: {}", emitter);
        log.info("emitter list size: {}", emitters.size());
        emitter.onCompletion(() -> {
            log.info("onCompletion callback");
            this.emitters.remove(emitter);    // 만료되면 리스트에서 삭제
        });
        emitter.onTimeout(() -> {
            log.info("onTimeout callback");
            emitter.complete();
        });

        return emitter;
    }
    public void scrapeChannels(int maxResults) throws Exception {
        SseEmitter emitter = emitters.get(0);
        List<String> channelIds = youtubeDataApiService.channelList(maxResults);
        List<channelDTO> success = new ArrayList<>();
        List<channelDTO> fail = new ArrayList<>();
        for(int i = 0; i < channelIds.size(); i++) {
            String channelId = channelIds.get(i);
            channelDTO channel = scrapeService.scrapeChannel(channelId);
            try {
                channelService.insertChannel(channel);
                success.add(channel);
                emitter.send(SseEmitter.event()
                        .name("success")
                        .data(channel));
            } catch(Exception e) {
                fail.add(channel);
                emitter.send(SseEmitter.event()
                        .name("fail")
                        .data(channel));
                log.info(e.getStackTrace().toString());
            }
        }
        HashMap<String, List<channelDTO>> hm = new HashMap<>();
        hm.put("success", success);
        hm.put("fail", fail);
        emitter.send(SseEmitter.event()
                .name("end")
                .data("end"));
    }
}
