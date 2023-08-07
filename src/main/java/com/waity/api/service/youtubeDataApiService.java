package com.waity.api.service;

import java.util.List;
public interface youtubeDataApiService {
    List<String> channelList(int maxResults) throws Exception;
}
