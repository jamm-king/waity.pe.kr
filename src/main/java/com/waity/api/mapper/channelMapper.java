package com.waity.api.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.waity.api.dto.channelDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface channelMapper {
	List<channelDTO> selectChannelAll() throws Exception;
	channelDTO selectChannelById(int id) throws Exception;
	List<channelDTO> selectChannelsByIds(List<Integer> ids) throws Exception;
	void updateChannel(channelDTO channel) throws Exception;
	void updateChannels(List<channelDTO> channel) throws Exception;
	void insertChannel(channelDTO channel) throws Exception;
	void insertChannels(List<channelDTO> channels) throws Exception;
	void deleteChannel(int id) throws Exception;
	void deleteChannels(List<Integer> id) throws Exception;
}
