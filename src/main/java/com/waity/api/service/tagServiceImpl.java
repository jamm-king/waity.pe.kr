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
	public List<tagDTO> selectTagByNames(String[] names) throws Exception {
		return tagMapper.selectTagByNames(names);
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
	public void deleteTag(tagDTO tag) throws Exception {
		tagMapper.deleteTag(tag);
	}
}