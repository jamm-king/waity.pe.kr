package com.waity.api.controller;

import java.util.List;


import com.waity.api.dto.channelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.waity.api.dto.tagDTO;
import com.waity.api.service.tagService;
import com.waity.api.service.channelTagService;

@RestController
public class tagController {

	private tagService tagService;
	private channelTagService channelTagService;

	public tagController(tagService tagService, channelTagService channelTagService) {
		this.tagService = tagService;
		this.channelTagService = channelTagService;
	}
	@GetMapping("/api/tag")
	public List<tagDTO> selectTagAll() throws Exception {
		List<tagDTO> tagList = tagService.selectTagAll();
		return tagList;
	}
	@GetMapping(path = "/api/tag/{id}")
	public tagDTO selectTagById(@PathVariable("id") int id) throws Exception {
		System.out.println("GET selectTagById");
		return tagService.selectTagById(id);
	}
	@GetMapping(value = "/api/tag", params = "channelId")
	public List<tagDTO> selectTagByChannelId(@RequestParam("channelId") int channelId) throws Exception {
		return channelTagService.selectTagByChannelId(channelId);
	}
	@PostMapping("/api/tag")
	public void insertTag(@RequestBody tagDTO tag) throws Exception {
		tagService.insertTag(tag);
	}
	@PutMapping("/api/tag")
	public void updateTag(@RequestBody tagDTO tag) throws Exception {
		tagService.updateTag(tag);
	}
	@DeleteMapping("/api/tag/{id}")
	public void deleteTag(@PathVariable int id) throws Exception {
		tagService.deleteTag(id);
	}
}