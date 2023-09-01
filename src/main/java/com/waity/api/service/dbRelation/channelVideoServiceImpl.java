package com.waity.api.service.dbRelation;

import com.waity.api.domain.Channel;
import com.waity.api.domain.Video;
import com.waity.api.global.error.exception.ErrorCode;
import com.waity.api.global.error.exception.customRuntimeException;
import com.waity.api.mapper.videoMapper;
import com.waity.api.mapper.channelVideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class channelVideoServiceImpl implements channelVideoService {

    private channelVideoMapper channelVideoMapper;
    private videoMapper videoMapper;

    @Autowired
    public channelVideoServiceImpl(channelVideoMapper channelVideoMapper) {
        this.channelVideoMapper = channelVideoMapper;
    }

    @Override
    public Channel selectFirstBySecond(int videoId) throws Exception {
        return Channel.createChannel(channelVideoMapper.selectChannelByVideo(videoId));
    }
    @Override
    public List<Video> selectSecondByFirst(int channelId) throws Exception {
        return Video.createVideos(channelVideoMapper.selectVideosByChannel(channelId));
    }
    @Override
    public void insertRelation(int channelId, int videoId) throws Exception {
        throw new customRuntimeException(ErrorCode.INVALID_OPERATION, "Operation is not supported. A video can be related to only one channel.");
    }
    @Override
    public void insertRelations(int channelId, List<Integer> videoIds) throws Exception {
        throw new customRuntimeException(ErrorCode.INVALID_OPERATION, "Operation is not supported. A video can be related to only one channel.");
    }
    @Override
    public void deleteRelation(int channelId, int videoId) throws Exception {
        throw new customRuntimeException(ErrorCode.INVALID_OPERATION, "Operation is not supported. A video must be related to any channel.");
    }
    @Override
    public void deleteRelations(int channelId, List<Integer> videoIds) throws Exception {
        throw new customRuntimeException(ErrorCode.INVALID_OPERATION, "Operation is not supported. A video must be related to any channel.");
    }
    @Override
    @Transactional
    public void updateRelations(int channelId, List<Integer> videoIds) throws Exception {
        throw new customRuntimeException(ErrorCode.INVALID_OPERATION, "Operation is not supported. Changing the video's relation is invalid.");
    }
}
