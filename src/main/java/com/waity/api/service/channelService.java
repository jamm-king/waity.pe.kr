package com.waity.api.service;

import com.waity.api.dto.channelDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

public interface channelService {
	//List<channelDTO> ChannelList(List<channelDTO> channelList) throws Exception;
	List<channelDTO> selectChannelAll() throws Exception;
	channelDTO selectChannelById(int id) throws Exception;
	List<channelDTO> selectChannelByIds(String[] ids) throws Exception;
	List<channelDTO> selectChannelByTags(String[] tags) throws Exception;

	void updateChannel(channelDTO channel) throws Exception;
	void insertChannel(channelDTO channel) throws Exception;
	public void insertChannels(List<channelDTO> channels) throws Exception;
	void deleteChannel(int id) throws Exception;

	// 채널-태그 관계에 대한 메소드
	void insertChannelTags(int channelId, List<Integer> tagIds) throws Exception;
	void updateChannelTags(int channelId, List<Integer> tagIds) throws Exception;
	void deleteChannelTags(int channelId, List<Integer> tagIds) throws Exception;

	// youtubeDataApi 통해 얻은 channelId로 스크래핑하여 채널 저장
	HashMap<String, List<channelDTO>> createChannels(int maxResults) throws Exception;
}
