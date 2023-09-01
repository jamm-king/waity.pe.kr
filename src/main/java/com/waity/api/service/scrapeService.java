package com.waity.api.service;

import com.waity.api.domain.Channel;


public interface scrapeService {
    Channel scrapeChannel(String channelId) throws Exception;
}
