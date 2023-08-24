package com.waity.api.service;

import com.waity.api.dto.channelDTO;
import com.waity.api.dto.tagDTO;
import com.waity.api.mapper.channelTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Qualifier("channelKingtagService")
public class channelKingtagServiceImpl implements channelKingtagService {

    private entityService<tagDTO> normaltagService;
    private channelTagMapper channelTagMapper;

    @Autowired
    public channelKingtagServiceImpl(normaltagService normaltagService, channelTagMapper channelTagMapper) {
        this.normaltagService = normaltagService;
        this.channelTagMapper = channelTagMapper;
    }
    @Override
    public List<channelDTO> selectChannelsByTag(int tagId) throws Exception {
        return channelTagMapper.selectChannelsByKingtag(tagId);
    }
    @Override
    public List<channelDTO> selectChannelsByTagNames(String[] tags) throws Exception {
        return channelTagMapper.selectChannelsByTagNames(tags);
    }
    @Override
    public List<tagDTO> selectTagByChannelId(int channelId) throws Exception {
        List<tagDTO> tags = new ArrayList<>(channelTagMapper.selectKingtagByChannel(channelId));
        return tags;
    }
    @Override
    public void insertChannelTags(int channelId, List<Integer> tagIds) throws Exception {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("channelId", channelId);
        hm.put("tagIds", tagIds);
        channelTagMapper.insertChannelKingtags(hm);
    }
    @Override
    public void updateChannelTags(int channelId, List<Integer> tagIds) throws Exception {
        List<tagDTO> originTags = selectTagByChannelId(channelId);
        List<tagDTO> targetTags = normaltagService.selectEntitiesByIds(tagIds);
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
        channelTagMapper.deleteChannelKingtags(hm);
    }
}
