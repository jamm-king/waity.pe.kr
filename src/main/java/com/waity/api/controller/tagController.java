package com.waity.api.controller;

import java.util.List;

import com.waity.api.service.entity.channelEntityService;
import com.waity.api.service.entity.tagEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.waity.api.dto.tagDTO;

@RestController
public class tagController {

	private channelEntityService channelEntityService;
	private tagEntityService tagEntityService;

	@Autowired
	public tagController(tagEntityService tagEntityService, channelEntityService channelEntityService) {
		this.channelEntityService = channelEntityService;
		this.tagEntityService = tagEntityService;
	}
	@GetMapping("/api/tag")
	public List<tagDTO> getAllTags() throws Exception {
		return tagEntityService.getAllTags();
	}
	@GetMapping(path = "/api/tag/{id}")
	public tagDTO getTag(@PathVariable("id") int id) throws Exception {
		return tagEntityService.getTag(id);
	}
	@GetMapping(value = "/api/tag", params = "channelId")
	public List<tagDTO> getTagsByChannel(@RequestParam("channelId") int channelId) throws Exception {
		return channelEntityService.getTags(channelId);
	}
	@PostMapping("/api/tag")
	public void createTag(@RequestBody tagDTO tag) throws Exception {
		tagEntityService.createTag(tag);
	}
	@PutMapping("/api/tag")
	public void updateTag(@RequestBody tagDTO tag) throws Exception {
		tagEntityService.updateTag(tag);
	}
	@DeleteMapping("/api/tag/{id}")
	public void deleteTag(@PathVariable int id) throws Exception {
		tagEntityService.deleteTag((id));
	}
}