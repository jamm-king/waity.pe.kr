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

import com.waity.api.service.tagService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("kingtagService")
public class kingtagServiceImpl implements tagService{
	
	@Autowired
	private kingtagMapper kingtagMapper;
	@Autowired
	private tagMapper tagMapper;


	public List<tagDTO> selectTagAll() throws Exception {
		List<kingtagDTO> kingtags = kingtagMapper.selectKingtagAll();
		List<tagDTO> tags = new ArrayList<>(kingtags);
		return tags;
	}
	public List<tagDTO> selectTagByNames(String[] names) throws Exception {
		List<kingtagDTO> kingtags = kingtagMapper.selectKingtagByNames(names);
		List<tagDTO> tags = new ArrayList<>(kingtags);
		return tags;
	}

	@Override
	public tagDTO selectTagById(int id) throws Exception {
		return kingtagMapper.selectKingTagById(id);
	}
	@Override
	public List<tagDTO> selectTagByIds(List<Integer> ids) throws Exception {
		return null;
	}

	@Override
	public List<tagDTO> selectTagByChannelId(int channelId) throws Exception {
		List<tagDTO> tags = new ArrayList<>(kingtagMapper.selectKingTagByChannelId(channelId));
		return tags;
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
