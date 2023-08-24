package com.waity.api.controller;

import java.util.ArrayList;
import java.util.List;


import com.waity.api.dto.tagDTO;
import com.waity.api.service.entityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import com.waity.api.dto.kingtagDTO;
import com.waity.api.service.tagService;

@CrossOrigin
@RestController
public class kingtagController {

	private entityService<tagDTO> tagService;

	public kingtagController(@Qualifier("kingtagService") tagService tagService){
		this.tagService = tagService;
	}
	@GetMapping("/api/kingtag")
	public List<kingtagDTO> selectKingtagAll() throws Exception {
		List<kingtagDTO> kingtagList = new ArrayList<>();
		List<tagDTO> tagList = tagService.selectEntityAll();
		tagList.forEach(tag->kingtagList.add((kingtagDTO) tag));
		return kingtagList;
	}
	@PostMapping("/api/kingtag")
	public void insertKingTag(@RequestBody kingtagDTO kingtag) throws Exception {
		tagService.insertEntity(kingtag);
	}
	@PutMapping("/api/kingtag")
	public void updateKingTag(@RequestBody kingtagDTO kingtag) throws Exception {
		tagService.updateEntity(kingtag);
	}
	@DeleteMapping("/api/kingtag/{id}")
	public void deleteKingTag(@PathVariable int id) throws Exception {
		tagService.deleteEntity(id);
	}
}
