package com.waity.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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

	public List<tagDTO> transformList(List<kingtagDTO> kingtags) throws Exception {
		List<tagDTO> tags = new ArrayList<tagDTO>();
		IntStream.range(0,kingtags.size()).forEach(i -> {
			tagDTO tag = new tagDTO();
			tag.tagName = kingtags.get(i).tagName;
			tags.add(tag);
		});
		return tags;
	}
	public List<kingtagDTO> selectEntityAll() throws Exception {
		List<kingtagDTO> kingtags = kingtagMapper.selectKingtagAll();
		return kingtags;
	}
	@Override
	public kingtagDTO selectEntityById(int id) throws Exception {
		return kingtagMapper.selectKingTagById(id);
	}
	@Override
	public List<kingtagDTO> selectEntitiesByIds(List<Integer> ids) throws Exception {
		return kingtagMapper.selectKingTagsByIds(ids);
	}
	@Transactional
	@Override
	public void insertEntity(kingtagDTO kingtag) throws Exception {
		tagMapper.insertTag(kingtag);
		kingtagMapper.insertKingTag(kingtag);
	}
	@Transactional
	@Override
	public void insertEntities(List<kingtagDTO> kingtags) throws Exception {
		List<tagDTO> tags = transformList(kingtags);
		tagMapper.insertTags(tags);

		IntStream.range(0,kingtags.size()).forEach(i -> {
			kingtags.get(i).id = tags.get(i).id;
		});
		kingtagMapper.insertKingTags(kingtags);
	}
	@Transactional
	@Override
	public void updateEntity(kingtagDTO kingtag) throws Exception {
		tagMapper.updateTag(kingtag);
		kingtagMapper.updateKingTag(kingtag);
	}
	@Transactional
	@Override
	public void updateEntities(List<kingtagDTO> kingtags) throws Exception {
		List<tagDTO> tags = transformList(kingtags);
		tagMapper.updateTags(tags);
		kingtagMapper.updateKingTags(kingtags);
	}
	@Override
	public void deleteEntity(int id) throws Exception {
		kingtagMapper.deleteKingTag(id);
	}
	@Override
	public void deleteEntities(List<Integer> ids) throws Exception {
		kingtagMapper.deleteKingTags(ids);
	}
}
