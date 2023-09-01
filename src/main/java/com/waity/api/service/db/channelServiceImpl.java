package com.waity.api.service.db;

import java.util.List;

import com.waity.api.domain.Channel;
import com.waity.api.domain.Tag;
import com.waity.api.domain.Video;
import com.waity.api.mapper.channelMapper;
import com.waity.api.mapper.videoMapper;
import com.waity.api.service.dbRelation.channelTagService;
import com.waity.api.service.dbRelation.channelVideoService;
import com.waity.api.service.dbRelation.manyToManyService;
import com.waity.api.service.dbRelation.oneToManyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class channelServiceImpl implements channelService {

	private channelMapper channelMapper;
	private videoMapper videoMapper;
	private manyToManyService<Channel, Tag> channelTagService;
	private oneToManyService<Channel, Video> channelVideoService;

	@Autowired
	public channelServiceImpl(channelMapper channelMapper, videoMapper videoMapper,
							  channelTagService channelTagService, channelVideoService channelVideoService) {
		this.channelMapper = channelMapper;
		this.videoMapper = videoMapper;
		this.channelTagService = channelTagService;
		this.channelVideoService = channelVideoService;
	}

	@Override
	public List<Channel> selectEntityAll() throws Exception {
		List<Channel> channels = Channel.createChannels(channelMapper.selectChannelAll());
		for(Channel channel : channels) {
			List<Tag> tags = channelTagService.selectSecondByFirst(channel.getId());
			List<Video> videos = channelVideoService.selectSecondByFirst(channel.getId());
			channel.setTags(tags);
			channel.setVideos(videos);
		}
		return channels;
	}
	@Override
	public Channel selectEntityById(int id) throws Exception {
		Channel channel = Channel.createChannel(channelMapper.selectChannelById(id));
		channel.setTags(channelTagService.selectSecondByFirst(id));
		channel.setVideos(channelVideoService.selectSecondByFirst(id));
		return channel;
	}
	@Override
	public List<Channel> selectEntitiesByIds(List<Integer> ids) throws Exception {
		List<Channel> channels = Channel.createChannels(channelMapper.selectChannelsByIds(ids));
		for(Channel channel : channels) {
			List<Tag> tags = channelTagService.selectSecondByFirst(channel.getId());
			List<Video> videos = channelVideoService.selectSecondByFirst(channel.getId());
			channel.setTags(tags);
			channel.setVideos(videos);
		}
		return channels;
	}
	@Override
	public void updateEntity(Channel channel) throws Exception {
		channelTagService.updateRelations(channel.getId(), channel.getTagIds());
		videoMapper.deleteVideos(channel.getVideoIds());
		videoMapper.insertVideos(Video.toVideoDTOs(channel.getVideos()));
		channelMapper.updateChannel(Channel.toChannelDTO(channel));
	}
	@Override
	public void updateEntities(List<Channel> channels) throws Exception {
		for (Channel channel : channels) {
			updateEntity(channel);
		}
	}
	@Override
	public void insertEntity(Channel channel) throws Exception {
		channelMapper.insertChannel(Channel.toChannelDTO(channel));
		channelTagService.insertRelations(channel.getId(), channel.getTagIds());
		videoMapper.insertVideos(Video.toVideoDTOs(channel.getVideos()));
	}
	@Override
	public void insertEntities(List<Channel> channels) throws Exception {
		for(Channel channel : channels) {
			insertEntity(channel);
		}
	}
	@Override
	public void deleteEntity(int id) throws Exception {
		channelMapper.deleteChannel(id);
	}
	@Override
	public void deleteEntities(List<Integer> ids) throws Exception {
		for(int id : ids) {
			deleteEntity(id);
		}
	}
}
