package com.waity.api.mapper;

import com.waity.api.dto.channelDTO;
import com.waity.api.dto.videoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface channelVideoMapper {
    channelDTO selectChannelByVideo(int videoId) throws Exception;
    List<videoDTO> selectVideosByChannel(int channelId) throws Exception;
}
