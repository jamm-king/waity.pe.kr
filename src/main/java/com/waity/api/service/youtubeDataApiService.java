package com.waity.api.service;

import java.util.List;
public interface youtubeDataApiService {

    List<String> videosList(int maxResults) throws Exception;
}
