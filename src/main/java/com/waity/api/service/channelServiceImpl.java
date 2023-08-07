package com.waity.api.service;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.waity.api.dto.channelDTO;
import com.waity.api.dto.tagDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waity.api.dto.videoDTO;
import com.waity.api.mapper.channelMapper;
import com.waity.api.mapper.videoMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
public class channelServiceImpl implements channelService {
	@Autowired
	private channelMapper channelMapper;
	@Autowired
	private tagService tagService;
	@Autowired
	private videoService videoService;
	@Autowired
	private youtubeDataApiService youtubeDataApiService;
	@Autowired
	private scrapeService scrapeService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
	public channelDTO selectChannelById(int id) throws Exception {
		return channelMapper.selectChannelById(id);
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
	public void insertChannels(List<channelDTO> channels) throws Exception {
		channelMapper.insertChannels(channels);
	}
	@Override
	@Transactional
	public void deleteChannel(int id) throws Exception {
		System.out.println("Channel Service: deleteChannel");
		videoService.deleteVideoByChannel(id);
		channelMapper.deleteChannel(id);
	}

	@Override
	public void insertChannelTags(int channelId, List<Integer> tagIds) throws Exception {
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("channelId", channelId);
		hm.put("tagIds", tagIds);
		channelMapper.insertChannelTags(hm);
	}
	@Transactional
	@Override
	public void updateChannelTags(int channelId, List<Integer> tagIds) throws Exception {
		List<tagDTO> originTags = tagService.selectTagByChannelId(channelId);
		List<tagDTO> targetTags = tagService.selectTagByIds(tagIds);
		List<Integer> originTagIds = new ArrayList<>();
		List<Integer> targetTagIds = new ArrayList<>();

		for(int i = 0; i < originTags.size(); i++) {
			originTagIds.add(originTags.get(i).getId());
		}
		for(int i = 0; i < targetTags.size(); i++) {
			targetTagIds.add(targetTags.get(i).getId());
		}
		deleteChannelTags(channelId, originTagIds);
		insertChannelTags(channelId, targetTagIds);
	}
	@Override
	public void deleteChannelTags(int channelId, List<Integer> tagIds) throws Exception {
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("channelId", channelId);
		hm.put("tagIds", tagIds);
		channelMapper.deleteChannelTags(hm);
	}

	@Override
	public HashMap<String, List<channelDTO>> createChannels(int maxResults) throws Exception {
		List<String> channelIds = youtubeDataApiService.channelList(maxResults);
		List<channelDTO> success = new ArrayList<>();
		List<channelDTO> fail = new ArrayList<>();
		for(int i = 0; i < channelIds.size(); i++) {
			String channelId = channelIds.get(i);
			channelDTO channel = scrapeService.scrapeChannel(channelId);
			try {
				insertChannel(channel);
				success.add(channel);
			} catch(Exception e) {
				fail.add(channel);
				logger.info(e.getStackTrace().toString());
			}
		}
		HashMap<String, List<channelDTO>> hm = new HashMap<>();
		hm.put("success", success);
		hm.put("fail", fail);

		return hm;
	}
}
