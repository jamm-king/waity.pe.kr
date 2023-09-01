package com.waity.api.service.db;

import java.util.List;

import com.waity.api.domain.Channel;
import com.waity.api.domain.Tag;
import com.waity.api.domain.Video;
import com.waity.api.service.dbRelation.manyToManyService;
import com.waity.api.service.dbRelation.oneToManyService;
import com.waity.api.service.dbRelation.videoTagService;
import com.waity.api.service.dbRelation.channelVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waity.api.mapper.videoMapper;

@Service
public class videoServiceImpl implements videoService {

	private videoMapper videoMapper;
	private manyToManyService<Video, Tag> videoTagService;
	private oneToManyService<Channel, Video> channelVideoService;

	@Autowired
	public videoServiceImpl(videoMapper videoMapper, videoTagService videoTagService, channelVideoService channelVideoService) {
		this.videoMapper = videoMapper;
		this.videoTagService = videoTagService;
		this.channelVideoService = channelVideoService;
	}

	@Override
	public List<Video> selectEntityAll() throws Exception {
		List<Video> videos = Video.createVideos(videoMapper.selectVideoAll());
		for(Video video : videos) {
			List<Tag> tags = videoTagService.selectSecondByFirst(video.getId());
			Channel channel = channelVideoService.selectFirstBySecond(video.getId());
			video.setTags(tags);
			video.setChannel(channel);
		}
		return videos;
	}
	@Override
	public Video selectEntityById(int id) throws Exception {
		Video video = Video.createVideo(videoMapper.selectVideoById(id));
		video.setTags(videoTagService.selectSecondByFirst(id));
		video.setChannel(channelVideoService.selectFirstBySecond(id));
		return video;
	}
	@Override
	public List<Video> selectEntitiesByIds(List<Integer> ids) throws Exception {
		List<Video> videos = Video.createVideos(videoMapper.selectVideosByIds(ids));
		for(Video video : videos) {
			List<Tag> tags = videoTagService.selectSecondByFirst(video.getId());
			Channel channel = channelVideoService.selectFirstBySecond(video.getId());
			video.setTags(tags);
			video.setChannel(channel);
		}
		return videos;
	}
	@Override
	public void updateEntity(Video video) throws Exception {
		videoTagService.updateRelations(video.getId(), video.getTagIds());
		videoMapper.updateVideo(Video.toVideoDTO(video));
	}
	@Override
	public void updateEntities(List<Video> videos) throws Exception {
		for (Video video : videos) {
			updateEntity(video);
		}
	}
	@Override
	public void insertEntity(Video video) throws Exception {
		videoMapper.insertVideo(Video.toVideoDTO(video));
	}
	@Override
	public void insertEntities(List<Video> videos) throws Exception {
		for(Video video : videos) {
			insertEntity(video);
		}
	}
	@Override
	public void deleteEntity(int id) throws Exception {
		videoMapper.deleteVideo(id);
	}
	@Override
	public void deleteEntities(List<Integer> ids) throws Exception {
		for(int id : ids) {
			deleteEntity(id);
		}
	}
}
