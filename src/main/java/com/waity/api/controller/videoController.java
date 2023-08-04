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

	@GetMapping("/api/video")
	public List<videoDTO> selectVideoAll() throws Exception {
		List<videoDTO> videoList = videoService.selectVideoAll();
		return videoList;
	}

	@GetMapping(path = "/api/video/{id}")
	public videoDTO selectVideoById(@PathVariable("id") int id) throws Exception {
		videoDTO video = videoService.selectVideoById(id);
		return video;
	}

	@GetMapping(value = "/api/video", params = "channelIds")
	public List<videoDTO> selectVideoByChannelIds(@RequestParam("channelIds") int[] ids) throws Exception {
		List<videoDTO> videoList = videoService.selectVideoByChannelIds(ids);
		return videoList;
	}

	@PostMapping("/api/video")
	public void insertVideo(@RequestBody videoDTO video) throws Exception {
		videoService.insertVideo(video);
	}

	@PutMapping("/api/video")
	public void updateVideo(@RequestBody videoDTO video) throws Exception {
		videoService.updateVideo(video);
	}

	@DeleteMapping("/api/video/{id}")
	public void deleteVideo(@PathVariable int id) throws Exception {
		videoService.deleteVideo(id);
	}

}