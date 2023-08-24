package com.waity.api.controller;

import java.util.List;

import com.waity.api.dto.channelDTO;
import com.waity.api.service.channelVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.waity.api.dto.videoDTO;
import com.waity.api.service.videoService;
import com.waity.api.service.entityService;

@CrossOrigin
@RestController
public class videoController {

	private entityService<videoDTO> videoService;
	private channelVideoService channelVideoService;

	public videoController(videoService videoService, channelVideoService channelVideoService) {
		this.videoService = videoService;
		this.channelVideoService = channelVideoService;
	}
	@GetMapping("/api/video")
	public List<videoDTO> selectVideoAll() throws Exception {
		List<videoDTO> videoList = videoService.selectEntityAll();
		return videoList;
	}
	@GetMapping(path = "/api/video/{id}")
	public videoDTO selectVideoById(@PathVariable("id") int id) throws Exception {
		videoDTO video = videoService.selectEntityById(id);
		return video;
	}
	@GetMapping(value = "/api/video", params = "channelIds")
	public List<videoDTO> selectVideoByChannelIds(@RequestParam("channelIds") int[] ids) throws Exception {
		List<videoDTO> videoList = channelVideoService.selectVideoByChannelIds(ids);
		return videoList;
	}
	@PostMapping("/api/video")
	public void insertVideo(@RequestBody videoDTO video) throws Exception {
		videoService.insertEntity(video);
	}
	@PutMapping("/api/video")
	public void updateVideo(@RequestBody videoDTO video) throws Exception {
		videoService.updateEntity(video);
	}
	@DeleteMapping("/api/video/{id}")
	public void deleteVideo(@PathVariable int id) throws Exception {
		videoService.deleteEntity(id);
	}
}