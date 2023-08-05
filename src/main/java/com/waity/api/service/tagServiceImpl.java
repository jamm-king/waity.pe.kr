package com.waity.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waity.api.dto.tagDTO;
import com.waity.api.mapper.tagMapper;

@Service
public class tagServiceImpl implements tagService {

	@Autowired
	private tagMapper tagMapper;
	
	@Override
	public List<tagDTO> selectTagAll() throws Exception {
		return tagMapper.selectTagAll();
	}
	@Override
	public tagDTO selectTagById(int id) throws Exception {
		return tagMapper.selectTagById(id);
	}
	@Override
	public List<tagDTO> selectTagByNames(String[] names) throws Exception {
		return tagMapper.selectTagByNames(names);
	}

	@Override
	public List<tagDTO> selectTagByChannelId(int channelId) throws Exception {
		return tagMapper.selectTagByChannelId(channelId);
	}

	@Override
	public void insertTag(tagDTO tag) throws Exception {
		tagMapper.insertTag(tag);
	}

	@Override
	public void updateTag(tagDTO tag) throws Exception {
		tagMapper.updateTag(tag);
	}
	@Override
	public void deleteTag(int id) throws Exception {
		tagMapper.deleteTag(id);
	}
}