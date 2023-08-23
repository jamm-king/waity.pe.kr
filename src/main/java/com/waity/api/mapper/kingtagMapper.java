package com.waity.api.mapper;

import java.util.List;

import com.waity.api.dto.kingtagDTO;
import com.waity.api.dto.tagDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface kingtagMapper {
	List<kingtagDTO> selectKingtagAll() throws Exception;
	kingtagDTO selectKingTagById(int id) throws Exception;
	void insertKingTag(kingtagDTO kingtag) throws Exception;
	void updateKingTag(kingtagDTO kingtag) throws Exception;
	void deleteKingTag(int id) throws Exception;
}
