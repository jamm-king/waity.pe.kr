package com.waity.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waity.api.dto.videoDTO;
import com.waity.api.mapper.videoMapper;

@Service
public class videoServiceImpl implements videoService {
	
	@Autowired
	private videoMapper videoMapper;

	@Autowired
	public videoServiceImpl(videoMapper videoMapper) {
		this.videoMapper = videoMapper;
	}
	@Override
	public List<videoDTO> selectEntityAll() throws Exception {
		List<videoDTO> videoList = videoMapper.selectVideoAll();
		return videoList;
	}
	@Override
	public videoDTO selectEntityById(int id) throws Exception {
		videoDTO video = videoMapper.selectVideoById(id);
		return video;
	}
	@Override
	public List<videoDTO> selectEntitiesByIds(List<Integer> ids) throws Exception {
		List<videoDTO> videos = videoMapper.selectVideosByIds(ids);
		return videos;
	}
	@Override
	public void updateEntity(videoDTO video) throws Exception {
		videoMapper.updateVideo(video);
	}
	@Override
	public void updateEntities(List<videoDTO> videos) throws Exception {
		videoMapper.updateVideos(videos);
	}

	@Override
	public void insertEntity(videoDTO video) throws Exception {
		videoMapper.insertVideo(video);
	}
	@Override
	public void insertEntities(List<videoDTO> videos) throws Exception {
		videoMapper.insertVideos(videos);
	}
	@Override
	public void deleteEntity(int id) throws Exception {
		videoMapper.deleteVideo(id);
	}
	@Override
	public void deleteEntities(List<Integer> ids) throws Exception {
		videoMapper.deleteVideos(ids);
	}
}
