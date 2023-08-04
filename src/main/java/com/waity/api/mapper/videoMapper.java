package com.waity.api.mapper;

import java.util.List;

import com.waity.api.dto.channelDTO;
import org.apache.ibatis.annotations.Mapper;

import com.waity.api.dto.videoDTO;

@Mapper
public interface videoMapper {
	List<videoDTO> selectVideoAll() throws Exception;
	List<videoDTO> selectVideoByChannelIds(int[] ids) throws Exception;
	void insertVideo(videoDTO video) throws Exception;
	void deleteVideoByChannel(channelDTO channel) throws Exception;
}
