package com.waity.api.service.dbRelation;

import com.waity.api.domain.Channel;
import com.waity.api.domain.Tag;
import com.waity.api.domain.Video;
import com.waity.api.mapper.channelTagMapper;
import com.waity.api.mapper.channelVideoMapper;
import com.waity.api.mapper.videoTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Primary
public class channelNormaltagServiceImpl implements channelNormaltagService {

    private channelTagMapper channelTagMapper;
    private channelVideoMapper channelVideoMapper;
    private videoTagMapper videoTagMapper;

    @Autowired
    public channelNormaltagServiceImpl(channelTagMapper channelTagMapper, channelVideoMapper channelVideoMapper, videoTagMapper videoTagMapper) {
        this.channelTagMapper = channelTagMapper;
        this.channelVideoMapper = channelVideoMapper;
        this.videoTagMapper = videoTagMapper;
    }

    @Override
    public List<Channel> selectFirstBySecond(int tagId) throws Exception {
        List<Channel> channels = Channel.createChannels(channelTagMapper.selectChannelsByTag(tagId));
        return channels;
    }
    @Override
    public List<Tag> selectSecondByFirst(int channelId) throws Exception {
        List<Tag> tags = Tag.createTags(channelTagMapper.selectTagsByChannel((channelId)));
        return tags;
    }
    @Override
    public void insertRelation(int channelId, int tagId) throws Exception {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("channelId", channelId);
        hm.put("tagId", tagId);
        channelTagMapper.insertChannelTag(hm);
    }
    @Override
    public void insertRelations(int channelId, List<Integer> tagIds) throws Exception {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("channelId", channelId);
        hm.put("tagIds", tagIds);
        channelTagMapper.insertChannelTags(hm);
    }
    @Override
    public void insertRelations(List<Integer> channelIds, int tagId) throws Exception {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("channelIds", channelIds);
        hm.put("tagId", tagId);
        channelTagMapper.insertTagChannels(hm);
    }
    @Override
    public void deleteRelation(int channelId, int tagId) throws Exception {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("channelId", channelId);
        hm.put("tagId", tagId);
        channelTagMapper.deleteChannelTag(hm);
    }
    @Override
    public void deleteRelations(int channelId, List<Integer> tagIds) throws Exception {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("channelId", channelId);
        hm.put("tagIds", tagIds);
        channelTagMapper.deleteChannelTags(hm);
    }
    @Override
    public void deleteRelations(List<Integer> channelIds, int tagId) throws Exception {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("channelIds", channelIds);
        hm.put("tagId", tagId);
        channelTagMapper.deleteTagChannels(hm);
    }
    @Override
    @Transactional
    public void updateRelations(int channelId, List<Integer> tagIds) throws Exception {
        List<Tag> originTags = selectSecondByFirst(channelId);
        List<Integer> originTagIds = new ArrayList<>();
        originTags.forEach(originTag -> {
            originTagIds.add(originTag.getId());
        });
        deleteRelations(channelId, originTagIds);
        insertRelations(channelId, tagIds);
    }
    @Override
    @Transactional
    public void updateRelations(List<Integer> channelIds, int tagId) throws Exception {
        List<Channel> originChannels = selectFirstBySecond(tagId);
        List<Integer> originChannelIds = new ArrayList<>();
        originChannels.forEach(originChannel -> {
            originChannelIds.add(originChannel.getId());
        });
        deleteRelations(tagId, originChannelIds);
        insertRelations(tagId, channelIds);
    }
}
