package com.waity.api.mapper;

import com.waity.api.dto.videoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface channelVideoMapper {
    List<videoDTO> selectVideoByChannel(int channelId) throws Exception;
    List<videoDTO> selectVideoByChannels(int[] ids) throws Exception;
    void deleteVideoByChannel(int channelId) throws Exception;
}
