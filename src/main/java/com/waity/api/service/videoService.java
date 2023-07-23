package com.waity.api.service;

import java.util.List;

import com.waity.api.dto.videoDTO;

public interface videoService {
	List<videoDTO> selectVideoByChannelIds(int[] ids) throws Exception;
	void insertVideo(videoDTO video) throws Exception;
}
