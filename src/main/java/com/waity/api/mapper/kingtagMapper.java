package com.waity.api.mapper;

import java.util.List;

import com.waity.api.dto.kingtagDTO;
import com.waity.api.dto.tagDTO;

public interface kingtagMapper {
	List<kingtagDTO> selectKingtagAll() throws Exception;
	List<kingtagDTO> selectKingtagByNames(String[] names) throws Exception;
	kingtagDTO selectKingTagById(int id) throws Exception;

	List<kingtagDTO> selectKingTagByChannelId(int channelId) throws Exception;

	void insertKingTag(kingtagDTO kingtag) throws Exception;
	void updateKingTag(kingtagDTO kingtag) throws Exception;
	void deleteKingTag(int id) throws Exception;
}
