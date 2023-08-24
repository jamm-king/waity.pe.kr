package com.waity.api.service;

import com.waity.api.dto.channelDTO;
import com.waity.api.dto.videoDTO;
import com.waity.api.mapper.channelVideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class channelVideoServiceImpl implements  channelVideoService {

    private entityService<channelDTO> channelService;
    private channelVideoMapper channelVideoMapper;

    @Autowired
    public channelVideoServiceImpl(channelService channelService, channelVideoMapper channelVideoMapper) {
        this.channelService = channelService;
        this.channelVideoMapper = channelVideoMapper;
    }
    @Override
    public List<videoDTO> selectVideoByChannel(int id) throws Exception {
        return channelVideoMapper.selectVideoByChannel(id);
    }
    @Override
    public List<videoDTO> selectVideoByChannelIds(int[] ids) throws Exception {
        return channelVideoMapper.selectVideoByChannels(ids);
    }
    @Override
    @Transactional
    public void deleteVideoByChannel(int id) throws Exception {
        channelVideoMapper.deleteVideoByChannel(id);
        channelService.deleteEntity(id);
    }
}
