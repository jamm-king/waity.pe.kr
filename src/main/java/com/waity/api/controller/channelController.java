package com.waity.api.controller;

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

	@CrossOrigin
	@GetMapping("/api/channel")
	public List<channelDTO> selectChannelAll() throws Exception {
		return channelService.selectChannelAll();
	}

	@CrossOrigin
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

//	@CrossOrigin
//	@PutMapping("/api/channel")
//	public void updateChannel(@RequestBody channelDTO channel) throws Exception {
//		channelService.updateChannel(channel);
//	}

	@PutMapping("/api/channel")
	public ResponseEntity<String> updateChannel(@RequestBody channelDTO channel) throws Exception {
		HttpHeaders responseHeader = new HttpHeaders();
		responseHeader.add("Access-Control-Allow-Origin", "*");
		responseHeader.add("Access-Control-Allow-Methods", "PUT, OPTIONS");
		responseHeader.add("Access-Control-Allow-Headers", "*");
		responseHeader.add("Access-Control-Max-Age", "300");
		System.out.println("PUT request arrived");
		return new ResponseEntity<String>("hi", responseHeader, HttpStatus.OK);
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
