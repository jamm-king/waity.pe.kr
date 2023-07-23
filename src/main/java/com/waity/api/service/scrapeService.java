package com.waity.api.service;

import com.waity.api.dto.channelDTO;
import org.json.simple.JSONObject;


public interface scrapeService {

    channelDTO scrapeChannel(String channelId) throws Exception;
}
