package com.waity.api.service.dbRelation;

import com.waity.api.domain.Channel;
import com.waity.api.domain.Tag;
import com.waity.api.domain.Video;
import com.waity.api.mapper.channelVideoMapper;
import com.waity.api.mapper.videoTagMapper;
import com.waity.api.mapper.channelTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class videoTagServiceImpl implements videoTagService {

    private videoTagMapper videoTagMapper;
    private channelVideoMapper channelVideoMapper;
    private channelTagMapper channelTagMapper;

    @Autowired
    public videoTagServiceImpl(videoTagMapper videoTagMapper, channelVideoMapper channelVideoMapper, channelTagMapper channelTagMapper) {
        this.videoTagMapper = videoTagMapper;
        this.channelVideoMapper = channelVideoMapper;
        this.channelTagMapper = channelTagMapper;
    }

    @Override
    public List<Video> selectFirstBySecond(int tagId) throws Exception {
        List<Video> videos = Video.createVideos(videoTagMapper.selectVideosByTag(tagId));
        return videos;
    }
    @Override
    public List<Tag> selectSecondByFirst(int videoId) throws Exception {
        List<Tag> tags = Tag.createTags(videoTagMapper.selectTagsByVideo(videoId));
        return tags;
    }
    @Override
    public void insertRelation(int videoId, int tagId) throws Exception {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("videoId", videoId);
        hm.put("tagId", tagId);
        videoTagMapper.insertVideoTag(hm);
    }
    @Override
    public void insertRelations(int videoId, List<Integer> tagIds) throws Exception {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("videoId", videoId);
        hm.put("tagIds", tagIds);
        videoTagMapper.insertVideoTags(hm);
    }
    @Override
    public void insertRelations(List<Integer> videoIds, int tagId) throws Exception {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("videoIds", videoIds);
        hm.put("tagId", tagId);
        videoTagMapper.insertTagVideos(hm);
    }
    @Override
    public void deleteRelation(int videoId, int tagId) throws Exception {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("videoId", videoId);
        hm.put("tagId", tagId);
        videoTagMapper.deleteVideoTag(hm);
    }
    @Override
    public void deleteRelations(int videoId, List<Integer> tagIds) throws Exception {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("videoId", videoId);
        hm.put("tagIds", tagIds);
        videoTagMapper.deleteVideoTags(hm);
    }
    @Override
    public void deleteRelations(List<Integer> videoIds, int tagId) throws Exception {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("videoIds", videoIds);
        hm.put("tagId", tagId);
        videoTagMapper.deleteTagVideos(hm);
    }
    @Override
    @Transactional
    public void updateRelations(int videoId, List<Integer> tagIds) throws Exception {
        List<Tag> originTags = selectSecondByFirst(videoId);
        List<Integer> originTagIds = new ArrayList<>();
        originTags.forEach(originTag -> {
            originTagIds.add(originTag.getId());
        });
        deleteRelations(videoId, originTagIds);
        insertRelations(videoId, tagIds);
    }
    @Override
    @Transactional
    public void updateRelations(List<Integer> videoIds, int tagId) throws Exception {
        List<Video> originVideos = selectFirstBySecond(tagId);
        List<Integer> originVideoIds = new ArrayList<>();
        originVideos.forEach(originVideo -> {
            originVideoIds.add(originVideo.getId());
        });
        deleteRelations(tagId, originVideoIds);
        insertRelations(tagId, videoIds);
    }
}
