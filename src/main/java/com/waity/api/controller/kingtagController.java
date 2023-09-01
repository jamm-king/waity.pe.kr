package com.waity.api.controller;

import java.util.ArrayList;
import java.util.List;

import com.waity.api.service.entity.kingtagEntityService;
import org.springframework.web.bind.annotation.*;

import com.waity.api.dto.kingtagDTO;

@CrossOrigin
@RestController
public class kingtagController {

	private kingtagEntityService kingtagEntityService;

	public kingtagController(kingtagEntityService kingtagEntityService){
		this.kingtagEntityService = kingtagEntityService;
	}
	@GetMapping("/api/kingtag")
	public List<kingtagDTO> getAllKingtags() throws Exception {
		return kingtagEntityService.getAllKingtags();
	}
	@PostMapping("/api/kingtag")
	public void createKingtag(@RequestBody kingtagDTO kingtag) throws Exception {
		kingtagEntityService.createKingtag(kingtag);
	}
	@PutMapping("/api/kingtag")
	public void updateKingtag(@RequestBody kingtagDTO kingtag) throws Exception {
		kingtagEntityService.updateKingtag(kingtag);
	}
	@DeleteMapping("/api/kingtag/{id}")
	public void deleteKingtag(@PathVariable int id) throws Exception {
		kingtagEntityService.deleteKingtag(id);
	}
}
