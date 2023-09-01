package com.waity.api.service.db;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.waity.api.domain.KingTag;
import com.waity.api.domain.Tag;
import com.waity.api.dto.tagDTO;
import com.waity.api.service.dbRelation.oneToOneService;
import com.waity.api.service.dbRelation.tagKingtagService;
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

	public List<KingTag> selectEntityAll() throws Exception {
		List<KingTag> kingtags = KingTag.createKingTags(kingtagMapper.selectKingtagAll());
		for(KingTag kingtag : kingtags) {
			kingtag.setParentTag(KingTag.createKingTag(kingtagMapper.selectParentTag(kingtag.getId())));
		}
		return kingtags;
	}
	@Override
	public KingTag selectEntityById(int id) throws Exception {
		KingTag kingtag = KingTag.createKingTag(kingtagMapper.selectKingTagById(id));
		kingtag.setParentTag(KingTag.createKingTag(kingtagMapper.selectParentTag(kingtag.getId())));
		return kingtag;
	}
	@Override
	public List<KingTag> selectEntitiesByIds(List<Integer> ids) throws Exception {
		List<KingTag> kingtags = KingTag.createKingTags(kingtagMapper.selectKingTagsByIds(ids));
		for(KingTag kingtag : kingtags) {
			kingtag.setParentTag(KingTag.createKingTag(kingtagMapper.selectParentTag(kingtag.getId())));
		}
		return kingtags;
	}
	@Transactional
	@Override
	public void insertEntity(KingTag kingtag) throws Exception {
		tagMapper.insertTag(Tag.toTagDTO(kingtag));
		kingtagMapper.insertKingTag(KingTag.toKingtagDTO(kingtag));
	}
	@Transactional
	@Override
	public void insertEntities(List<KingTag> kingtags) throws Exception {
		List<kingtagDTO> kingtagDTOs = KingTag.toKingtagDTOs(kingtags);
		List<tagDTO> tags = upcastList(kingtagDTOs);
		tagMapper.insertTags(tags);

		IntStream.range(0,kingtags.size()).forEach(i -> {
			kingtags.get(i).setId(tags.get(i).getId());
		});
		kingtagMapper.insertKingTags(KingTag.toKingtagDTOs(kingtags));
	}
	@Transactional
	@Override
	public void updateEntity(KingTag kingtag) throws Exception {
		tagMapper.updateTag(Tag.toTagDTO(kingtag));
		kingtagMapper.updateKingTag(KingTag.toKingtagDTO(kingtag));
	}
	@Transactional
	@Override
	public void updateEntities(List<KingTag> kingtags) throws Exception {
		List<kingtagDTO> kingtagDTOs = KingTag.toKingtagDTOs(kingtags);
		List<tagDTO> tags = upcastList(kingtagDTOs);
		tagMapper.updateTags(tags);
		kingtagMapper.updateKingTags(kingtagDTOs);
	}
	@Override
	public void deleteEntity(int id) throws Exception {
		kingtagMapper.deleteKingTag(id);
	}
	@Override
	public void deleteEntities(List<Integer> ids) throws Exception {
		kingtagMapper.deleteKingTags(ids);
	}

	public List<tagDTO> upcastList(List<kingtagDTO> kingtags) throws Exception {
		List<tagDTO> tags = new ArrayList<tagDTO>();
		IntStream.range(0,kingtags.size()).forEach(i -> {
			tagDTO tag = new tagDTO(
					kingtags.get(i).getId(),
					kingtags.get(i).getTagName()
			);
		});
		return tags;
	}
}
