package com.waity.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.waity.api.dto.tagDTO;

@Mapper
public interface tagMapper {
	List<tagDTO> selectTagAll() throws Exception;
	tagDTO selectTagById(int id) throws Exception;
	List<tagDTO> selectTagByNames(String[] names) throws Exception;
	List<tagDTO> selectTagByChannelId(int channelId) throws Exception;
	void insertTag(tagDTO tag) throws Exception;
	void updateTag(tagDTO tag) throws Exception;
	void deleteTag(int id) throws Exception;
}