package com.waity.api.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.waity.api.dto.kingtagDTO;
import com.waity.api.service.kingtagService;

@CrossOrigin
@RestController
public class kingtagController {
	@Autowired
	private kingtagService kingtagService;
	
	@GetMapping("/api/kingtag")
	public List<kingtagDTO> selectKingtagAll() throws Exception {
		List<kingtagDTO> kingtagList = kingtagService.selectKingtagAll();
		return kingtagList;
	}
	@GetMapping(value = "/api/kingtag", params = "names")
	public List<kingtagDTO> selectKingtagByNames(@RequestParam("names") String[] names) throws Exception {
		List<kingtagDTO> kingtagList = kingtagService.selectKingtagByNames(names);
		return kingtagList;
	}
	@PostMapping("/api/kingtag")
	public void insertKingTag(@RequestBody kingtagDTO kingtag) throws Exception {
		kingtagService.insertKingTag(kingtag);
	}

	@PutMapping("/api/kingtag")
	public void updateKingTag(@RequestBody kingtagDTO kingtag) throws Exception {
		kingtagService.updateKingTag(kingtag);
	}

	@DeleteMapping("/api/kingtag")
	public void deleteKingTag(@RequestBody kingtagDTO kingtag) throws Exception {
		kingtagService.deleteKingTag(kingtag);
	}

}
