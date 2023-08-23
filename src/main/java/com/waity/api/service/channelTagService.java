package com.waity.api.service;

import com.waity.api.dto.channelDTO;
import com.waity.api.dto.tagDTO;

import java.util.List;

public interface channelTagService {
    List<channelDTO> selectChannelsByTag(int id) throws Exception;
    List<channelDTO> selectChannelsByTagNames(String[] tags) throws Exception;
    List<tagDTO> selectTagByChannelId(int channelId) throws Exception;
    void insertChannelTags(int channelId, List<Integer> tagIds) throws Exception;
    void updateChannelTags(int channelId, List<Integer> tagIds) throws Exception;
    void deleteChannelTags(int channelId, List<Integer> tagIds) throws Exception;
}
