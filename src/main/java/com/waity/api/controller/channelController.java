package com.waity.api.controller;

import java.util.HashMap;
import java.util.List;


import com.waity.api.dto.channelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.waity.api.service.channelService;

@RestController
public class channelController {
	@Autowired
	private channelService channelService;

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
	@PostMapping("/api/channel")
	public void insertChannel(@RequestBody channelDTO channel) throws Exception {
		channelService.insertChannel(channel);
	}
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
	public void inserChannelTags(@RequestBody HashMap<String, Object> hm) throws Exception {
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
