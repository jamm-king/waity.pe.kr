package com.waity.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.waity.api.dto.videoDTO;

@Mapper
public interface videoMapper {
	List<videoDTO> selectVideoByChannelIds(int[] ids) throws Exception;
	void insertVideo(videoDTO video) throws Exception;
}
