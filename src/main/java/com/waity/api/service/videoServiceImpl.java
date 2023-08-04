package com.waity.api.service;

import java.util.List;

import com.waity.api.dto.channelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waity.api.dto.videoDTO;
import com.waity.api.mapper.videoMapper;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class videoServiceImpl implements videoService {
	
	@Autowired
	private videoMapper videoMapper;

	@Override
	public List<videoDTO> selectVideoAll() throws Exception {
		List<videoDTO> videoList = videoMapper.selectVideoAll();
		return videoList;
	}

	@Override
	public void updateVideo(videoDTO video) throws Exception {
		videoMapper.updateVideo(video);
	}

	@Override
	public videoDTO selectVideoById(int id) throws Exception {
		videoDTO video = videoMapper.selectVideoById(id);
		return video;
	}

	@Override
	public List<videoDTO> selectVideoByChannelIds(int[] ids) throws Exception {
		return videoMapper.selectVideoByChannelIds(ids);
	}

	@Override
	public void insertVideo(videoDTO video) throws Exception {
		videoMapper.insertVideo(video);
	}

	@Override
	public void deleteVideoByChannel(channelDTO channel) throws Exception {
		System.out.println("Video Service: deleteVideoByChannel");
		videoMapper.deleteVideoByChannel(channel);
	}
}
