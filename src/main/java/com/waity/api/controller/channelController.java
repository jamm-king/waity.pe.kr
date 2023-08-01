package com.waity.api.controller;

import java.util.List;


import com.waity.api.dto.channelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import com.waity.api.service.channelService;

@CrossOrigin(
		origins="*",
		methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
		allowedHeaders="*",
		allowCredentials="true")
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

	@PutMapping("/api/channel")
	public void updateChannel(@RequestBody channelDTO channel) throws Exception {
		channelService.updateChannel(channel);
	}

	@PostMapping("/api/channel")
	public void insertChannel(@RequestBody channelDTO channel) throws Exception {
		channelService.insertChannel(channel);
	}

	@DeleteMapping("/api/channel")
	public void deleteChannel(@RequestBody channelDTO channel) throws Exception{
		channelService.deleteChannel(channel);
	}
}
