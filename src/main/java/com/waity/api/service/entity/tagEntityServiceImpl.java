package com.waity.api.service.entity;

import com.waity.api.domain.Channel;
import com.waity.api.domain.Tag;
import com.waity.api.domain.Video;
import com.waity.api.dto.channelDTO;
import com.waity.api.dto.tagDTO;
import com.waity.api.dto.videoDTO;
import com.waity.api.service.db.dbService;
import com.waity.api.service.db.channelService;
import com.waity.api.service.db.tagService;
import com.waity.api.service.db.videoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class tagEntityServiceImpl implements tagEntityService {

    private dbService<Channel> channelService;
    private dbService<Tag> tagService;
    private dbService<Video> videoService;

    @Autowired
    public tagEntityServiceImpl(channelService channelService, tagService tagService, videoService videoService) {
        this.channelService = channelService;
        this.tagService = tagService;
        this.videoService = videoService;
    }

    @Override
    public List<tagDTO> getAllTags() throws Exception {
        List<Tag> tags = tagService.selectEntityAll();
        List<tagDTO> tagDTOs = Tag.toTagDTOs(tags);
        return tagDTOs;
    }
    @Override
    public tagDTO getTag(int id) throws Exception {
        Tag tag = tagService.selectEntityById(id);
        tagDTO tagDTO = Tag.toTagDTO(tag);
        return tagDTO;
    }
    @Override
    public List<tagDTO> getTags(List<Integer> ids) throws Exception {
        List<Tag> tags = tagService.selectEntitiesByIds(ids);
        List<tagDTO> tagDTOs = Tag.toTagDTOs(tags);
        return tagDTOs;
    }
    @Override
    public void createTag(tagDTO tagDTO) throws Exception {
        Tag tag = Tag.createTag(tagDTO);
        tagService.insertEntity(tag);
    }
    @Override
    public void updateTag(tagDTO tagDTO) throws Exception {
        Tag tag = tagService.selectEntityById(tagDTO.getId());
        tag.update(tagDTO);
        tagService.updateEntity(tag);
    }
    @Override
    public void updateTags(List<tagDTO> tagDTOs) throws Exception {
        List<Integer> ids = new ArrayList<>();
        for(tagDTO tagDTO: tagDTOs) {
            ids.add(tagDTO.getId());
        }
        List<Tag> tags = tagService.selectEntitiesByIds(ids);
        IntStream.range(0, tags.size()).forEach(i -> {
            tags.get(i).update(tagDTOs.get(i));
        });
        tagService.updateEntities(tags);
    }
    @Override
    public void deleteTag(int id) throws Exception {
        tagService.deleteEntity(id);
    }
    @Override
    public void deleteTags(List<Integer> ids) throws Exception {
        tagService.deleteEntities(ids);
    }
    @Override
    public void connectChannelsToTag(int tagId, List<Integer> channelIds) throws Exception {
        Tag tag = tagService.selectEntityById(tagId);
        List<Channel> channels = channelService.selectEntitiesByIds(channelIds);
        tag.addChannels(channels);
        tagService.updateEntity(tag);
    }
    @Override
    public void disconnectChannelsFromTag(int tagId, List<Integer> channelIds) throws Exception {
        Tag tag = tagService.selectEntityById(tagId);
        List<Channel> channels = channelService.selectEntitiesByIds(channelIds);
        tag.removeChannels(channels);
        tagService.updateEntity(tag);
    }
    @Override
    public List<channelDTO> getChannels(int tagId) throws Exception {
        Tag tag = tagService.selectEntityById(tagId);
        List<channelDTO> channelDTOs = Channel.toChannelDTOs(tag.getChannels());
        return channelDTOs;
    }
    @Override
    public List<videoDTO> getVideos(int tagId) throws Exception {
        Tag tag = tagService.selectEntityById(tagId);
        List<videoDTO> videoDTOs = Video.toVideoDTOs(tag.getVideos());
        return videoDTOs;
    }
}
