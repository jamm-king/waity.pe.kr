package com.waity.api.service;

import java.util.List;

import com.waity.api.dto.tagDTO;

public interface tagService {
	List<tagDTO> selectTagAll() throws Exception;
	List<tagDTO> selectTagByNames(String[] names) throws Exception;

	void insertTag(tagDTO tag) throws Exception;
	void updateTag(tagDTO tag) throws Exception;
	void deleteTag(tagDTO tag) throws Exception;
}
