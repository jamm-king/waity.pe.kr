package com.waity.api.controller;

import java.util.List;

import com.waity.api.service.entity.videoEntityService;
import org.springframework.web.bind.annotation.*;

import com.waity.api.dto.videoDTO;

@CrossOrigin
@RestController
public class videoController {

	private videoEntityService videoEntityService;

	public videoController(videoEntityService videoEntityService) {
		this.videoEntityService = videoEntityService;
	}
	@GetMapping("/api/video")
	public List<videoDTO> getAllVideos() throws Exception {
		return videoEntityService.getAllVideos();
	}
	@GetMapping(path = "/api/video/{id}")
	public videoDTO getVideo(@PathVariable("id") int id) throws Exception {
		return videoEntityService.getVideo(id);
	}
	@PostMapping("/api/video")
	public void createVideo(@RequestBody videoDTO video) throws Exception {
		videoEntityService.createVideo(video);
	}
	@PutMapping("/api/video")
	public void updateVideo(@RequestBody videoDTO video) throws Exception {
		videoEntityService.updateVideo(video);
	}
	@DeleteMapping("/api/video/{id}")
	public void deleteVideo(@PathVariable int id) throws Exception {
		videoEntityService.deleteVideo(id);
	}
}