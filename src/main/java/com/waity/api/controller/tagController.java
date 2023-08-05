package com.waity.api.controller;

import java.util.List;


import com.waity.api.dto.channelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.waity.api.dto.tagDTO;
import com.waity.api.service.tagService;

@RestController
public class tagController {

	@Autowired
	private tagService tagService;
	
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
//	@GetMapping("/api/tag/{names}")
//	public List<tagDTO> selectTagByName(@PathVariable("names") String[] names) throws Exception {
//		List<tagDTO> tagList = tagService.selectTagByNames(names);
//		return tagList;
//	}
	@GetMapping(value = "/api/tag", params = "names")
	public List<tagDTO> selectTagByName(@RequestParam("names") String[] names) throws Exception {
		List<tagDTO> tagList = tagService.selectTagByNames(names);
		return tagList;
	}

	@GetMapping(value = "/api/tag", params = "channelId")
	public List<tagDTO> selectTagByChannelId(@RequestParam("channelId") int channelId) throws Exception {
		return tagService.selectTagByChannelId(channelId);
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