package com.waity.api.service.entity;

import com.waity.api.domain.Channel;
import com.waity.api.domain.Tag;
import com.waity.api.domain.Video;
import com.waity.api.dto.videoDTO;
import com.waity.api.service.db.channelService;
import com.waity.api.service.db.dbService;
import com.waity.api.service.db.tagService;
import com.waity.api.service.db.videoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class videoEntityServiceImpl implements videoEntityService {

    private dbService<Channel> channelService;
    private dbService<Tag> tagService;
    private dbService<Video> videoService;

    @Autowired
    public videoEntityServiceImpl(channelService channelService, tagService tagService, videoService videoService) {
        this.channelService = channelService;
        this.tagService = tagService;
        this.videoService = videoService;
    }

    @Override
    public List<videoDTO> getAllVideos() throws Exception {
        List<Video> videos = videoService.selectEntityAll();
        List<videoDTO> videoDTOs = Video.toVideoDTOs(videos);
        return videoDTOs;
    }

    @Override
    public videoDTO getVideo(int id) throws Exception {
        Video video = videoService.selectEntityById(id);
        videoDTO videoDTO = Video.toVideoDTO(video);
        return videoDTO;
    }

    @Override
    public void createVideo(videoDTO videoDTO) throws Exception {
        Video video = Video.createVideo(videoDTO);
        videoService.insertEntity(video);
    }

    @Override
    public void updateVideo(videoDTO videoDTO) throws Exception {
        Video video = videoService.selectEntityById(videoDTO.getId());
        video.update(videoDTO);
        videoService.updateEntity(video);
    }

    @Override
    public void deleteVideo(int id) throws Exception {
        videoService.deleteEntity(id);
    }
}
