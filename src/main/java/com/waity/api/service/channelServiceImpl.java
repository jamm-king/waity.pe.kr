package com.waity.api.service;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.waity.api.dto.channelDTO;
import com.waity.api.dto.tagDTO;
import com.waity.api.global.error.exception.ErrorCode;
import com.waity.api.global.error.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waity.api.dto.videoDTO;
import com.waity.api.mapper.channelMapper;
import com.waity.api.mapper.videoMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@Slf4j
public class channelServiceImpl implements channelService {

	private channelMapper channelMapper;

	@Autowired
	public channelServiceImpl(channelMapper channelMapper) {
		this.channelMapper = channelMapper;
	}
	@Override
	public List<channelDTO> selectChannelAll() throws Exception {
		return channelMapper.selectChannelAll();
	}
	@Override
	public channelDTO selectChannelById(int id) throws Exception {
		channelDTO channel = channelMapper.selectChannelById(id);
		if(channel == null) {
			throw new NotFoundException("entity not found.", ErrorCode.ENTITY_NOT_FOUND);
		}
		return channel;
	}
	@Override
	public List<channelDTO> selectChannelByIds(String[] ids) throws Exception {
		return channelMapper.selectChannelByIds(ids);
	}
	@Override
	public void updateChannel(channelDTO channel) throws Exception {
		channelMapper.updateChannel(channel);
	}
	@Override
	public void insertChannel(channelDTO channel) throws Exception {
		channelMapper.insertChannel(channel);
	}
	@Override
	public void insertChannels(List<channelDTO> channels) throws Exception {
		channelMapper.insertChannels(channels);
	}
	@Override
	public void deleteChannel(int id) throws Exception {
		channelMapper.deleteChannel(id);
	}
}
