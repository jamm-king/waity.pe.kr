package com.waity.api.service;

import java.util.List;

import com.waity.api.dto.tagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waity.api.dto.kingtagDTO;
import com.waity.api.mapper.kingtagMapper;

import com.waity.api.service.tagService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class kingtagServiceImpl implements kingtagService{
	
	@Autowired
	private kingtagMapper kingtagMapper;

	@Autowired
	private tagService tagService;
	
	public List<kingtagDTO> selectKingtagAll() throws Exception {
		return kingtagMapper.selectKingtagAll();
	}
	public List<kingtagDTO> selectKingtagByNames(String[] names) throws Exception {
		return kingtagMapper.selectKingtagByNames(names);
	}

	@Transactional
	public void insertKingTag(kingtagDTO kingtag) throws Exception {
		tagDTO tag = new tagDTO();
		tag.tagName = kingtag.tagName;
		tagService.insertTag(tag);

		kingtag.id = tag.id;
		kingtagMapper.insertKingTag(kingtag);
	}
	@Transactional
	public void updateKingTag(kingtagDTO kingtag) throws Exception {
		tagDTO tag = new tagDTO();
		tag.tagName = kingtag.tagName;
		tag.id = kingtag.id;
		tagService.updateTag(tag);

		kingtagMapper.updateKingTag(kingtag);
	}
	public void deleteKingTag(kingtagDTO kingtag) throws Exception {
		kingtagMapper.deleteKingTag(kingtag);
	}
}
