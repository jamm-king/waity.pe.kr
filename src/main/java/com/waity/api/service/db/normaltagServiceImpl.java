package com.waity.api.service.db;

import com.waity.api.domain.Channel;
import com.waity.api.domain.Tag;
import com.waity.api.domain.Video;
import com.waity.api.mapper.tagMapper;
import com.waity.api.service.dbRelation.manyToManyService;
import com.waity.api.service.dbRelation.channelTagService;
import com.waity.api.service.dbRelation.videoTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class normaltagServiceImpl implements normaltagService {

    private tagMapper tagMapper;
    private manyToManyService<Channel, Tag> channelTagService;
    private manyToManyService<Video, Tag> videoTagService;

    @Autowired
    public normaltagServiceImpl(tagMapper tagMapper, channelTagService channelTagService, videoTagService videoTagService) {
        this.tagMapper = tagMapper;
        this.channelTagService = channelTagService;
        this.videoTagService = videoTagService;
    }
    @Override
    public List<Tag> selectEntityAll() throws Exception {
        List<Tag> tags = Tag.createTags(tagMapper.selectTagAll());
        for(Tag tag : tags) {
            tag.setChannels(channelTagService.selectFirstBySecond(tag.getId()));
            tag.setVideos(videoTagService.selectFirstBySecond(tag.getId()));
        }
        return tags;
    }
    @Override
    public Tag selectEntityById(int id) throws Exception {
        Tag tag = Tag.createTag(tagMapper.selectTagById(id));
        tag.setChannels(channelTagService.selectFirstBySecond(id));
        tag.setVideos(videoTagService.selectFirstBySecond(id));
        return tag;
    }
    @Override
    public List<Tag> selectEntitiesByIds(List<Integer> ids) throws Exception {
        List<Tag> tags = Tag.createTags(tagMapper.selectTagsByIds(ids));
        for(Tag tag : tags) {
            List<Channel> channels = channelTagService.selectFirstBySecond(tag.getId());
            List<Video> videos = videoTagService.selectFirstBySecond(tag.getId());
            tag.setChannels(channels);
            tag.setVideos(videos);
        }
        return tags;
    }
    @Override
    public void updateEntity(Tag tag) throws Exception {
        channelTagService.updateRelations(tag.getChannelIds(), tag.getId());
        videoTagService.updateRelations(tag.getVideoIds(), tag.getId());
        tagMapper.updateTag(Tag.toTagDTO(tag));
    }
    @Override
    public void updateEntities(List<Tag> tags) throws Exception {
        for (Tag tag : tags) {
            updateEntity(tag);
        }
    }
    @Override
    public void insertEntity(Tag tag) throws Exception {
        tagMapper.insertTag(Tag.toTagDTO(tag));
        channelTagService.insertRelations(tag.getChannelIds(), tag.getId());
        videoTagService.insertRelations(tag.getVideoIds(), tag.getId());
    }
    @Override
    public void insertEntities(List<Tag> tags) throws Exception {
        for(Tag tag : tags) {
            insertEntity(tag);
        }
    }
    @Override
    public void deleteEntity(int id) throws Exception {
        tagMapper.deleteTag(id);
    }
    @Override
    public void deleteEntities(List<Integer> ids) throws Exception {
        for(int id : ids) {
            deleteEntity(id);
        }
    }
}
