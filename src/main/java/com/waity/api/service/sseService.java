package com.waity.api.service;

import com.waity.api.domain.Channel;
import com.waity.api.dto.channelDTO;
import com.waity.api.service.db.dbService;
import com.waity.api.service.db.channelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class sseService {

    private ConcurrentHashMap<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    private youtubeDataApiService youtubeDataApiService;
    private scrapeService scrapeService;
    private dbService<Channel> channelService;

    @Autowired
    public sseService(youtubeDataApiService youtubeDataApiService, scrapeService scrapeService, channelService channelService) {
        this.youtubeDataApiService = youtubeDataApiService;
        this.scrapeService = scrapeService;
        this.channelService = channelService;
    }
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
        List<channelDTO> success = new ArrayList<>();
        List<channelDTO> fail = new ArrayList<>();
        HashMap<String, Object> exception = new HashMap<>();

        for(int i = 0; i < channelIds.size(); i++) {
            String channelId = channelIds.get(i);
            try {
                Channel channel = scrapeService.scrapeChannel(channelId);
                try {
                    channelService.insertEntity(channel);
                    success.add(Channel.toChannelDTO(channel));
                } catch (Exception e) {
                    fail.add(Channel.toChannelDTO(channel));
                    throw e;
                } finally {
                    emitter.send(SseEmitter.event()
                            .name("progress")
                            .data(String.format("%d/%d", i + 1, channelIds.size())));
                }
            } catch (Exception e) {
                exception.put(channelId, e.toString());
                log.warn(e.getStackTrace().toString());
            }
        }
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("success", success);
        hm.put("fail", fail);
        hm.put("exception", exception);
        emitter.send(SseEmitter.event()
                .name("end")
                .data(hm));
        emitter.complete();
    }
}
