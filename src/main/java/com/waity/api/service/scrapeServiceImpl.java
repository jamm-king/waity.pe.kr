package com.waity.api.service;

import java.util.*;

import com.jayway.jsonpath.JsonPath;
import com.waity.api.domain.Channel;
import com.waity.api.service.db.channelService;
import net.minidev.json.JSONArray;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class scrapeServiceImpl implements scrapeService{

    SseEmitter sseEmitter;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private youtubeDataApiService youtubeDataApiService;
    private com.waity.api.service.db.channelService channelService;

    @Autowired
    public scrapeServiceImpl(youtubeDataApiService youtubeDataApiService, channelService channelService) {
        this.youtubeDataApiService = youtubeDataApiService;
        this.channelService = channelService;
    }
    public Object parse(String url) throws Exception {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Elements script = doc.getElementsByTag("script");
        int numElements = script.size();
        String s = "";
        for(int i=0;i<numElements;i++) {
            Element element = script.get(i);
            s = element.html();
            if(s.contains("var ytInitialData =")) {
                s = s.replace("var ytInitialData = ","");
                s = s.replace(";","");
                break;
            }
        }
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(s);

        return obj;
    }
    public Channel scrapeChannel(String channelId) throws Exception {
        String url = "https://www.youtube.com/channel/" + channelId;
        Object obj = parse(url);

        //keyword
        //keyword 파싱해서 넣어야함
        String keywords = ((JSONArray)JsonPath.read(obj.toString(),"$..keywords")).get(0).toString();
        List<String> keywordList = new ArrayList<String>();

        keywords = keywords.replace(",", "");
        String word = "";
        boolean swch1 = false;
        boolean swch2 = false;
        for(int i = 0; i < keywords.length(); i++) {
            if(keywords.charAt(i) == '"') {
                if(swch1) {
                    keywordList.add(word);
                    word = "";
                    swch1 = false;
                    swch2 = true;
                } else {
                    swch1 = true;
                }
            } else {
                if(keywords.charAt(i) == ' ') {
                    if(swch1) {
                        word = word + keywords.charAt(i);
                    } else {
                        if(swch2) {
                            swch2 = false;
                        } else {
                            keywordList.add(word);
                            word = "";
                        }
                    }
                } else {
                    word = word + keywords.charAt(i);
                }
            }
        }
        if(!word.equals("")) {
            keywordList.add(word);
        }
        keywords = "";
        for(int i = 0; i < keywordList.size(); i++) {
            keywords = keywords + keywordList.get(i) + ",";
        }
        if(keywords.length() != 0){
            keywords = keywords.substring(0, keywords.length() - 1);
        }

        //title
        String title = ((JSONArray)JsonPath.read(obj.toString(),"$..header..title")).get(0).toString();

        //image
        String image = ((JSONArray)JsonPath.read(obj.toString(),"$..header..avatar..url")).get(0).toString();

        //구독자 수
        obj = parse(url+"/about");
        String subscriberCount = ((JSONArray)JsonPath.read(obj.toString(),"$..subscriberCountText.simpleText")).get(0).toString();

        //description
        String description = "";
        description = ((JSONArray) JsonPath.read(obj.toString(), "$..description.simpleText")).get(0).toString();

        //조회수
        //조회수도 파싱해서 넣어야함
		String viewCountString = ((JSONArray)JsonPath.read(obj.toString(),"$..viewCountText.simpleText")).get(0).toString();

        int subscriptionCount = 0;
        
        Pattern pattern = Pattern.compile("(구독자 (\\d+[.]\\d+(천|만|억)|(\\d+(천|만|억))|\\d+)명)");
        Matcher matcher = pattern.matcher(subscriberCount);
        if(matcher.find()) {
            String temp = matcher.group();
            String sub = temp.replaceAll("구독자|명| ", "");
            String subNumStr = sub.replaceAll("천|만|억", "");
            if (temp.contains("천")) {
                float subNumFloat = Float.parseFloat(subNumStr) * 1000;
                subscriptionCount = (int) subNumFloat;
            } else if (temp.contains("만")) {
                float subNumFloat = Float.parseFloat(subNumStr) * 10000;
                subscriptionCount = (int) subNumFloat;
            } else if (temp.contains("억")) {
                float subNumFloat = Float.parseFloat(subNumStr) * 100000000;
                subscriptionCount = (int) subNumFloat;
            }
        }
        Long viewCount = null;
        pattern = Pattern.compile("조회수 [\\d,]+회");
        matcher = pattern.matcher(viewCountString);
        if (matcher.find()) {
            String temp = matcher.group();
            temp = temp.replaceAll("조회수| |회|,", "");
            viewCount = Long.parseLong(temp);
        }

        Channel channel = new Channel();
        channel.setChannelId(channelId);
        channel.setTitle(title);
        channel.setImage(image);
        channel.setKeyword(keywords);
        channel.setDescription(description);
        channel.setViewCount(viewCount);
        channel.setSubscriptionCount(subscriptionCount);

        logger.info("###############\nscraped data for " + title + "###############");

        return channel;
    }
}
