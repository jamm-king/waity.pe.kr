package com.waity.api.mapper;

import java.util.List;


import com.waity.api.dto.channelDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface channelMapper {
	List<channelDTO> selectChannelAll() throws Exception;
	List<channelDTO> selectChannelByIds(String[] names) throws Exception;
	List<channelDTO> selectChannelByTags(String[] tags) throws Exception;
	void updateChannel(channelDTO channel) throws Exception;
	void insertChannel(channelDTO channel) throws Exception;
	void deleteChannel(channelDTO channel) throws Exception;
}
