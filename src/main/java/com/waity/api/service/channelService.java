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
	void updateChannel(channelDTO channel) throws Exception;
	void insertChannel(channelDTO channel) throws Exception;
	void insertChannels(List<channelDTO> channels) throws Exception;
	void deleteChannel(int id) throws Exception;
}
