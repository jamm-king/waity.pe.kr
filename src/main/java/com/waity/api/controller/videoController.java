package com.waity.api.controller;

import java.util.List;

import com.waity.api.dto.channelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.waity.api.dto.videoDTO;
import com.waity.api.service.videoService;

@CrossOrigin
@RestController
public class videoController {
	
	@Autowired
	private videoService videoService;

	@GetMapping(value = "/api/video", params = "channelIds")
	public List<videoDTO> selectVideoByChannelIds(@RequestParam("channelIds") int[] ids) throws Exception {
		List<videoDTO> videoList = videoService.selectVideoByChannelIds(ids);
		return videoList;
	}

	@PostMapping("/api/video")
	public void insertVideo(@RequestBody videoDTO video) throws Exception {
		videoService.insertVideo(video);
	}


}