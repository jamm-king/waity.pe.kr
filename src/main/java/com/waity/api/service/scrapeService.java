package com.waity.api.service;

import com.waity.api.dto.channelDTO;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.List;


public interface scrapeService {
    channelDTO scrapeChannel(String channelId) throws Exception;
}
