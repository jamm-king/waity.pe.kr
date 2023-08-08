package com.waity.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.waity.api.dto.channelDTO;
import com.waity.api.service.scrapeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.waity.api.service.channelService;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class channelController {
	@Autowired
	private channelService channelService;
	@Autowired
	private scrapeService scrapeService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/api/channel")
	public List<channelDTO> selectChannelAll() throws Exception {
		return channelService.selectChannelAll();
	}
	@GetMapping(path = "/api/channel/{id}")
	public channelDTO selectChannelById(@PathVariable("id") int id) throws Exception {
		return channelService.selectChannelById(id);
	}
	@GetMapping(value = "/api/channel", params = "ids")
	public List<channelDTO> selectChannelByIds(@RequestParam("ids") String[] ids) throws Exception {
		return channelService.selectChannelByIds(ids);
	}
	@GetMapping(value = "/api/channel", params = "tags")
	public List<channelDTO> selectChannelByTags(@RequestParam("tags") String[] tags) throws Exception {
		return channelService.selectChannelByTags(tags);
	}
//	@PostMapping(value = "/api/channel")
//	public SseEmitter insertChannels(@RequestBody HashMap<String, String> requestBody) throws Exception {
//		int maxResults = Integer.parseInt(requestBody.get("maxResults"));
//		return scrapeService.scrapeChannels(maxResults);
//	}
	@PutMapping("/api/channel")
	public void updateChannel(@RequestBody channelDTO channel) throws Exception {
		channelService.updateChannel(channel);
	}
	@DeleteMapping("/api/channel/{id}")
	public void deleteChannel(@PathVariable int id) throws Exception{
		System.out.println("Channel Controller: deleteChannel");
		channelService.deleteChannel(id);
	}

	@PostMapping("/api/channel/tag")
	public void insertChannelTags(@RequestBody HashMap<String, Object> hm) throws Exception {
		int channelId = (int)hm.get("channelId");
		List<Integer> tagIds = (List<Integer>)hm.get("tagIds");
		channelService.insertChannelTags(channelId, tagIds);
	}
	@PutMapping("/api/channel/tag")
	public void updateChannelTags(@RequestBody HashMap<String, Object> hm) throws Exception {
		int channelId = (int)hm.get("channelId");
		List<Integer> tagIds = (List<Integer>)hm.get("tagIds");
		channelService.updateChannelTags(channelId, tagIds);
	}
	@DeleteMapping("/api/channel/tag")
	public void deleteChannelTags(@RequestBody HashMap<String, Object> hm) throws Exception{
		int channelId = (int)hm.get("channelId");
		List<Integer> tagIds = (List<Integer>)hm.get("tagIds");
		channelService.deleteChannelTags(channelId, tagIds);
	}
}
