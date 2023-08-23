package com.waity.api.service;

import java.util.ArrayList;
import java.util.List;

import com.waity.api.dto.tagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.waity.api.dto.kingtagDTO;
import com.waity.api.mapper.kingtagMapper;
import com.waity.api.mapper.tagMapper;

import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("kingtagService")
public class kingtagServiceImpl implements kingtagService {

	private tagMapper tagMapper;
	private kingtagMapper kingtagMapper;

	@Autowired
	public kingtagServiceImpl(kingtagMapper kingtagMapper, tagMapper tagMapper) {
		this.tagMapper = tagMapper;
		this.kingtagMapper = kingtagMapper;
	}
	public List<tagDTO> selectTagAll() throws Exception {
		List<kingtagDTO> kingtags = kingtagMapper.selectKingtagAll();
		List<tagDTO> tags = new ArrayList<>(kingtags);
		return tags;
	}
	@Override
	public tagDTO selectTagById(int id) throws Exception {
		return kingtagMapper.selectKingTagById(id);
	}
	@Transactional
	public void insertTag(tagDTO kingtag) throws Exception {
		tagDTO tag = new tagDTO();
		tag.tagName = kingtag.tagName;
		tagMapper.insertTag(tag);

		kingtag.id = tag.id;
		kingtagMapper.insertKingTag((kingtagDTO) kingtag);
	}
	@Transactional
	public void updateTag(tagDTO kingtag) throws Exception {
		tagDTO tag = new tagDTO();
		tag.tagName = kingtag.tagName;
		tag.id = kingtag.id;
		tagMapper.updateTag(tag);
		kingtagMapper.updateKingTag((kingtagDTO) kingtag);
	}
	public void deleteTag(int id) throws Exception {
		kingtagMapper.deleteKingTag(id);
	}
}
