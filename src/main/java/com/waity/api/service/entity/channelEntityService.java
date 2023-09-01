package com.waity.api.service.entity;

import com.waity.api.dto.channelDTO;
import com.waity.api.dto.tagDTO;
import com.waity.api.dto.videoDTO;

import java.util.List;

public interface channelEntityService {
    List<channelDTO> getAllChannels() throws Exception;
    channelDTO getChannel(int id) throws Exception;
    List<channelDTO> getChannels(List<Integer> ids) throws Exception;
    void updateChannel(channelDTO channelDTO) throws Exception;
    void updateChannels(List<channelDTO> channelDTOs) throws Exception;
    void deleteChannel(int id) throws Exception;
    void deleteChannels(List<Integer> ids) throws Exception;

    void connectTagsToChannel(int channelId, List<Integer> tagIds) throws Exception;
    void disconnectTagsFromChannel(int channelId, List<Integer> tagIds) throws Exception;

    List<tagDTO> getTags(int channelId) throws Exception;
    List<videoDTO> getVideos(int channelId) throws Exception;
}
