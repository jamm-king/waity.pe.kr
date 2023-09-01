package com.waity.api.service.entity;

import com.waity.api.domain.Channel;
import com.waity.api.domain.Tag;
import com.waity.api.domain.Video;
import com.waity.api.dto.channelDTO;
import com.waity.api.dto.tagDTO;
import com.waity.api.dto.videoDTO;
import com.waity.api.global.error.exception.ErrorCode;
import com.waity.api.global.error.exception.NotFoundException;
import com.waity.api.service.db.channelService;
import com.waity.api.service.db.tagService;
import com.waity.api.service.db.dbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class channelEntityServiceImpl implements channelEntityService {

    private dbService<Channel> channelService;
    private dbService<Tag> tagService;

    @Autowired
    public channelEntityServiceImpl(channelService channelService, tagService tagService) {
        this.channelService = channelService;
        this.tagService = tagService;
    }

    @Override
    public List<channelDTO> getAllChannels() throws Exception {
        List<Channel> channels = channelService.selectEntityAll();
        List<channelDTO> channelDTOs = Channel.toChannelDTOs(channels);
        return channelDTOs;
    }
    @Override
    public channelDTO getChannel(int id) throws Exception {
        Channel channel = channelService.selectEntityById(id);
        if(channel == null) throw new NotFoundException(ErrorCode.ENTITY_NOT_FOUND);
        channelDTO channelDTO = Channel.toChannelDTO(channel);
        return channelDTO;
    }
    @Override
    public List<channelDTO> getChannels(List<Integer> ids) throws Exception {
        List<Channel> channels = channelService.selectEntitiesByIds(ids);
        if(channels == null) throw new NotFoundException(ErrorCode.ENTITY_NOT_FOUND);
        List<channelDTO> channelDTOs = Channel.toChannelDTOs(channels);
        return channelDTOs;
    }
    @Override
    public void updateChannel(channelDTO channelDTO) throws Exception {
        Channel channel = channelService.selectEntityById(channelDTO.getId());
        channel.update(channelDTO);
        channelService.updateEntity(channel);
    }
    @Override
    public void updateChannels(List<channelDTO> channelDTOs) throws Exception {
        List<Integer> ids = new ArrayList<>();
        for(channelDTO channelDTO : channelDTOs) {
            ids.add(channelDTO.getId());
        }
        List<Channel> channels = channelService.selectEntitiesByIds(ids);
        IntStream.range(0,channels.size()).forEach(i -> {
            channels.get(i).update(channelDTOs.get(i));
        });
        channelService.updateEntities(channels);
    }
    @Override
    public void deleteChannel(int id) throws Exception {
        channelService.deleteEntity(id);
    }
    @Override
    public void deleteChannels(List<Integer> ids) throws Exception {
        channelService.deleteEntities(ids);
    }
    @Override
    public void connectTagsToChannel(int channelId, List<Integer> tagIds) throws Exception {
        Channel channel = channelService.selectEntityById(channelId);
        List<Tag> tags = tagService.selectEntitiesByIds(tagIds);
        channel.addTags(tags);
        channelService.updateEntity(channel);
    }
    @Override
    public void disconnectTagsFromChannel(int channelId, List<Integer> tagIds) throws Exception {
        Channel channel = channelService.selectEntityById(channelId);
        List<Tag> tags = tagService.selectEntitiesByIds(tagIds);
        channel.removeTags(tags);
    }
    @Override
    public List<tagDTO> getTags(int channelId) throws Exception {
        Channel channel = channelService.selectEntityById(channelId);
        List<tagDTO> tagDTOs = Tag.toTagDTOs(channel.getTags());
        return tagDTOs;
    }
    @Override
    public List<videoDTO> getVideos(int channelId) throws Exception {
        Channel channel = channelService.selectEntityById(channelId);
        List<videoDTO> videoDTOs = Video.toVideoDTOs(channel.getVideos());
        return videoDTOs;
    }
}
