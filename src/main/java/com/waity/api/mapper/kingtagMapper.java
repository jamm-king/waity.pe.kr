package com.waity.api.mapper;

import java.util.HashMap;
import java.util.List;

import com.waity.api.dto.kingtagDTO;
import com.waity.api.dto.tagDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface kingtagMapper {
	List<kingtagDTO> selectKingtagAll() throws Exception;
	kingtagDTO selectKingTagById(int id) throws Exception;
	List<kingtagDTO> selectKingTagsByIds(List<Integer> ids) throws Exception;
	void insertKingTag(kingtagDTO kingtag) throws Exception;
	void insertKingTags(List<kingtagDTO> kingtag) throws Exception;
	void updateKingTag(kingtagDTO kingtag) throws Exception;
	void updateKingTags(List<kingtagDTO> kingtag) throws Exception;
	void deleteKingTag(int id) throws Exception;
	void deleteKingTags(List<Integer> ids) throws Exception;

	void insertTagParentTag(HashMap<Integer, Integer> hm) throws Exception;
	void updateTagParentTag(HashMap<Integer, Integer> hm) throws Exception;
	void deleteTagParentTag(HashMap<Integer, Integer> hm) throws Exception;

	tagDTO selectTagByKingtag(int kingtagId);
	kingtagDTO selectKingtagByTag(int tagId);
	kingtagDTO selectParentTag(int id);
}
