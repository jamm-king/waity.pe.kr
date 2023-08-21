package com.waity.api.service;

import java.util.List;

import com.waity.api.dto.kingtagDTO;
import com.waity.api.dto.tagDTO;

public interface tagService {
	List<tagDTO> selectTagAll() throws Exception;
	tagDTO selectTagById(int id) throws Exception;
	List<tagDTO> selectTagByIds(List<Integer> ids) throws Exception;
	List<tagDTO> selectTagByNames(String[] names) throws Exception;
	List<tagDTO> selectTagByChannelId(int channelId) throws Exception;
	void insertTag(tagDTO tag) throws Exception;
	void updateTag(tagDTO tag) throws Exception;
	void deleteTag(int id) throws Exception;

}
