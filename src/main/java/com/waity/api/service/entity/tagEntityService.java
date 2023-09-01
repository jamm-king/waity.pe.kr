package com.waity.api.service.entity;

import com.waity.api.dto.channelDTO;
import com.waity.api.dto.tagDTO;
import com.waity.api.dto.videoDTO;

import java.util.List;

public interface tagEntityService {
    List<tagDTO> getAllTags() throws Exception;
    tagDTO getTag(int id) throws Exception;
    List<tagDTO> getTags(List<Integer> ids) throws Exception;
    void createTag(tagDTO tag) throws Exception;
    void updateTag(tagDTO tag) throws Exception;
    void updateTags(List<tagDTO> tags) throws Exception;
    void deleteTag(int id) throws Exception;
    void deleteTags(List<Integer> ids) throws Exception;

    void connectChannelsToTag(int tagId, List<Integer> channelIds) throws Exception;
    void disconnectChannelsFromTag(int tagId, List<Integer> channelIds) throws Exception;

    List<channelDTO> getChannels(int tagId) throws Exception;
    List<videoDTO> getVideos(int tagId) throws Exception;
}
