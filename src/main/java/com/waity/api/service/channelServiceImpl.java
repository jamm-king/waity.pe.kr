package com.waity.api.service;

import java.util.ArrayList;

import java.util.List;

import com.waity.api.dto.channelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waity.api.dto.videoDTO;
import com.waity.api.mapper.channelMapper;
import com.waity.api.mapper.videoMapper;

@Service
public class channelServiceImpl implements channelService {
	@Autowired
	private channelMapper channelMapper;
	@Autowired
	private videoMapper videoMapper;
	
//	@Override
//	public List<channelDTO> ChannelList(List<channelDTO> channelList) throws Exception {
//		long startTime = System.currentTimeMillis();
//		List<channelDTO> channelVideoList = new ArrayList<channelDTO>();
//		int ids[] = new int[channelList.size()];
//		for(int i = 0; i < channelList.size(); i++) {
//			channelDTO channelObj = channelList.get(i);
//			ids[i] = channelObj.id;
//		}
//		List<videoDTO> videoList = videoMapper.selectVideoByChannelIds(ids);
//
//		int idx = 0;
//		for(int i=0;i<channelList.size();i++){
//			channelDTO channel = channelList.get(i);
//			int size = channel.videoCount;
//			List<videoDTO> videos = videoList.subList(idx,Math.min(idx+size,videoList.size()));
//			idx+=size;
//			channel.videos = videos;
//			channelVideoList.add(channel);
//		}
//
//		long estimatedTime = System.currentTimeMillis() - startTime;
//		System.out.println("estimatedTime : " + (estimatedTime) + "ms");
//		return channelVideoList;
//	}
	
	@Override
	public List<channelDTO> selectChannelAll() throws Exception {
		return channelMapper.selectChannelAll();
	}

	@Override
	public List<channelDTO> selectChannelByIds(String[] ids) throws Exception {
		return channelMapper.selectChannelByIds(ids);
	}

	@Override
	public List<channelDTO> selectChannelByTags(String[] tags) throws Exception {
		return channelMapper.selectChannelByTags(tags);
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
	public void deleteChannel(channelDTO channel) throws Exception {
		channelMapper.deleteChannel(channel);
	}

}
