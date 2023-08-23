package com.waity.api.service;

import com.waity.api.dto.tagDTO;

import java.util.List;

public interface normaltagService extends tagService {
    List<tagDTO> selectTagsByIds(List<Integer> ids) throws Exception;
}
