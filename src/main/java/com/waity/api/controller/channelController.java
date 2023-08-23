package com.waity.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.waity.api.dto.channelDTO;
import com.waity.api.service.channelTagService;
import com.waity.api.service.scrapeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.waity.api.service.channelService;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class channelController {

	private channelService channelService;
	private channelTagService channelTagService;

	public channelController(channelService channelService, channelTagService channelTagService) {
		this.channelService = channelService;
		this.channelTagService = channelTagService;
	}
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
	@GetMapping(value = "/api/channel", params = "tagId")
	public List<channelDTO> selectChannelByTags(@RequestParam("tagId") int tagId) throws Exception {
		return channelTagService.selectChannelsByTag(tagId);
	}
	@GetMapping(value = "/api/channel", params = "tags")
	public List<channelDTO> selectChannelByTags(@RequestParam("tags") String[] tags) throws Exception {
		return channelTagService.selectChannelsByTagNames(tags);
	}
	@PutMapping("/api/channel")
	public void updateChannel(@RequestBody channelDTO channel) throws Exception {
		channelService.updateChannel(channel);
	}
	@DeleteMapping("/api/channel/{id}")
	public void deleteChannel(@PathVariable int id) throws Exception{
		channelService.deleteChannel(id);
	}
	@PostMapping("/api/channel/tag")
	public void insertChannelTags(@RequestBody HashMap<String, Object> hm) throws Exception {
		int channelId = (int)hm.get("channelId");
		List<Integer> tagIds = (List<Integer>)hm.get("tagIds");
		channelTagService.insertChannelTags(channelId, tagIds);
	}
	@PutMapping("/api/channel/tag")
	public void updateChannelTags(@RequestBody HashMap<String, Object> hm) throws Exception {
		int channelId = (int)hm.get("channelId");
		List<Integer> tagIds = (List<Integer>)hm.get("tagIds");
		channelTagService.updateChannelTags(channelId, tagIds);
	}
	@DeleteMapping("/api/channel/tag")
	public void deleteChannelTags(@RequestBody HashMap<String, Object> hm) throws Exception{
		int channelId = (int)hm.get("channelId");
		List<Integer> tagIds = (List<Integer>)hm.get("tagIds");
		channelTagService.deleteChannelTags(channelId, tagIds);
	}
}
