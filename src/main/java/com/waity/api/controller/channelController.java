package com.waity.api.controller;

import java.util.HashMap;
import java.util.List;


import com.waity.api.dto.channelDTO;
import com.waity.api.mapper.channelTagMapper;
import com.waity.api.service.entity.channelEntityService;
import com.waity.api.service.entity.tagEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class channelController {

	private channelEntityService channelEntityService;
	private tagEntityService tagEntityService;
	@Autowired
	private channelTagMapper channelTagMapper;

	public channelController(channelEntityService channelEntityService, tagEntityService tagEntityService) {
		this.channelEntityService = channelEntityService;
		this.tagEntityService = tagEntityService;
	}
	@GetMapping("/api/channel")
	public List<channelDTO> getAllChannels() throws Exception {
		return channelEntityService.getAllChannels();
	}
	@GetMapping(path = "/api/channel/{id}")
	public channelDTO getChannel(@PathVariable("id") int id) throws Exception {
		return channelEntityService.getChannel(id);
	}
	@GetMapping(value = "/api/channel", params = "ids")
	public List<channelDTO> getChannels(@RequestParam("ids") List<Integer> ids) throws Exception {
		return channelEntityService.getChannels(ids);
	}
	@GetMapping(value = "/api/channel", params = "tagId")
	public List<channelDTO> getChannelsByTag(@RequestParam("tagId") int tagId) throws Exception {
		return tagEntityService.getChannels(tagId);
	}
	@PutMapping("/api/channel")
	public void updateChannel(@RequestBody channelDTO channel) throws Exception {
		channelEntityService.updateChannel(channel);
	}
	@PutMapping(value = "/api/channel", params = "multiple")
	public void updateChannels(@RequestBody List<channelDTO> channels) throws Exception {
		channelEntityService.updateChannels(channels);
	}
	@DeleteMapping("/api/channel/{id}")
	public void deleteChannel(@PathVariable int id) throws Exception{
		channelEntityService.deleteChannel(id);
	}
	@DeleteMapping(value = "/api/channel" , params= "ids")
	public void deleteChannels(@RequestParam List<Integer> ids) throws Exception{
		channelEntityService.deleteChannels(ids);
	}
	@PostMapping("/api/channel/tag")
	public void connectTagsToChannel(@RequestBody HashMap<String, Object> hm) throws Exception {
		int channelId = (int)hm.get("channelId");
		List<Integer> tagIds = (List<Integer>)hm.get("tagIds");
		channelEntityService.connectTagsToChannel(channelId, tagIds);
	}
	@DeleteMapping("/api/channel/tag")
	public void disconnectTagsFromChannel(@RequestBody HashMap<String, Object> hm) throws Exception{
		int channelId = (int)hm.get("channelId");
		List<Integer> tagIds = (List<Integer>)hm.get("tagIds");
		channelEntityService.disconnectTagsFromChannel(channelId, tagIds);
	}

	@GetMapping(value="/api/channel", params="tagid")
	public List<channelDTO> selectChannelsByTag(@RequestParam("tagid") int tagid) throws Exception {
		return channelTagMapper.selectChannelsByTag(tagid);
	}
}
