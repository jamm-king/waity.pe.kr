package com.waity.api.service;

import java.util.List;

import com.waity.api.dto.channelDTO;
import com.waity.api.dto.videoDTO;
import org.springframework.web.bind.annotation.PathVariable;

public interface videoService {
	List<videoDTO> selectVideoAll() throws Exception;
	videoDTO selectVideoById(int id) throws Exception;
	List<videoDTO> selectVideoByChannelIds(int[] ids) throws Exception;
	void insertVideo(videoDTO video) throws Exception;
	void updateVideo(videoDTO video) throws Exception;
	void deleteVideo(int id) throws Exception;
	void deleteVideoByChannel(int channelId) throws Exception;
}
