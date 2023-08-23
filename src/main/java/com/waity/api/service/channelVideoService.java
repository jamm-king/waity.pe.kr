package com.waity.api.service;

import com.waity.api.dto.videoDTO;

import java.util.List;

public interface channelVideoService {
    List<videoDTO> selectVideoByChannel(int id) throws Exception;
    List<videoDTO> selectVideoByChannelIds(int[] ids) throws Exception;
    void deleteVideoByChannel(int channelId) throws Exception;
}
