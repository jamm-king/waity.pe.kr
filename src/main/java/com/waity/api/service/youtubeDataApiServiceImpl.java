package com.waity.api.service;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class youtubeDataApiServiceImpl implements youtubeDataApiService{

    @Value("${youtube.key}")
    String key;
    @Value("${youtube.endpoint}")
    String endpoint;

    //API를 사용해서 최신 Video들을 받아오는 함수
    //받아온 Video에서 channelId를 추출하여 반환
    public List<String> videosList(int maxResults) throws Exception {
        String uri = endpoint + "/videos" + "?part=snippet " + "&key=" + key + "&maxResults=" + maxResults + "&regionCode=KR" + "&chart=mostPopular";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class);
        String responseBody = response.getBody();

        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(responseBody);
        List<String> channelIds = JsonPath.read(obj.toString(),"$.items..channelId");

        return channelIds;
    }
}
