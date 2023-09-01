package com.waity.api.service.entity;

import com.waity.api.dto.videoDTO;

import java.util.List;

public interface videoEntityService {
    List<videoDTO> getAllVideos() throws Exception;

    videoDTO getVideo(int id) throws Exception;

    void createVideo(videoDTO video) throws Exception;

    void updateVideo(videoDTO video) throws Exception;

    void deleteVideo(int id) throws Exception;
}
