package com.waity.api.mapper;

import java.util.List;

import com.waity.api.dto.kingtagDTO;

public interface kingtagMapper {
	List<kingtagDTO> selectKingtagAll() throws Exception;
	List<kingtagDTO> selectKingtagByNames(String[] names) throws Exception;
	void insertKingTag(kingtagDTO kingtag) throws Exception;
	void updateKingTag(kingtagDTO kingtag) throws Exception;
	void deleteKingTag(kingtagDTO kingtag) throws Exception;
}
