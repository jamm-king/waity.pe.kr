package com.waity.api.mapper;

import java.util.List;

import com.waity.api.dto.channelDTO;
import org.apache.ibatis.annotations.Mapper;

import com.waity.api.dto.videoDTO;
import org.springframework.web.bind.annotation.PathVariable;

@Mapper
public interface videoMapper {
	List<videoDTO> selectVideoAll() throws Exception;
	videoDTO selectVideoById(int id) throws Exception;
	List<videoDTO> selectVideosByIds(List<Integer> ids) throws Exception;
	void insertVideo(videoDTO video) throws Exception;
	void insertVideos(List<videoDTO> videos) throws Exception;
	void updateVideo(videoDTO video) throws Exception;
	void updateVideos(List<videoDTO> videos) throws Exception;
	void deleteVideo(int id) throws Exception;
	void deleteVideos(List<Integer> ids) throws Exception;
}
