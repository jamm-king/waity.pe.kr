package com.waity.api.service;

import com.waity.api.dto.channelDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface channelService {
	//List<channelDTO> ChannelList(List<channelDTO> channelList) throws Exception;
	List<channelDTO> selectChannelAll() throws Exception;
	List<channelDTO> selectChannelByIds(String[] ids) throws Exception;
	List<channelDTO> selectChannelByTags(String[] tags) throws Exception;

	void updateChannel(channelDTO channel) throws Exception;
	void insertChannel(channelDTO channel) throws Exception;
	void deleteChannel(channelDTO channel) throws Exception;
}
