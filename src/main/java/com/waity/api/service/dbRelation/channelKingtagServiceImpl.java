package com.waity.api.service.dbRelation;

import com.waity.api.dto.channelDTO;
import com.waity.api.dto.kingtagDTO;
import com.waity.api.mapper.channelTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Qualifier("channelKingtagService")
public class channelKingtagServiceImpl implements channelKingtagService {

    private channelTagMapper channelTagMapper;

    @Autowired
    public channelKingtagServiceImpl(channelTagMapper channelTagMapper) {
        this.channelTagMapper = channelTagMapper;
    }
    @Override
    public List<channelDTO> selectFirstBySecond(int kingtagId) throws Exception {
        return channelTagMapper.selectChannelsByKingtag(kingtagId);
    }
    @Override
    public List<kingtagDTO> selectSecondByFirst(int channelId) throws Exception {
       return channelTagMapper.selectKingtagsByChannel(channelId);
    }
    @Override
    public void insertRelation(int channelId, int kingtagId) throws Exception {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("channelId", channelId);
        hm.put("tagId", kingtagId);
        channelTagMapper.insertChannelKingtag(hm);
    }
    @Override
    public void insertRelations(int channelId, List<Integer> kingtagIds) throws Exception {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("channelId", channelId);
        hm.put("tagIds", kingtagIds);
        channelTagMapper.insertChannelKingtags(hm);
    }
    @Override
    public void insertRelations(List<Integer> channelIds, int kingtagId) throws Exception {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("channelIds", channelIds);
        hm.put("tagId", kingtagId);
        channelTagMapper.insertKingtagChannels(hm);
    }
    @Override
    public void deleteRelation(int channelId, int tagId) throws Exception {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("channelId", channelId);
        hm.put("tagIds", tagId);
        channelTagMapper.deleteChannelKingtag(hm);
    }
    @Override
    public void deleteRelations(int channelId, List<Integer> kingtagIds) throws Exception {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("channelId", channelId);
        hm.put("tagIds", kingtagIds);
        channelTagMapper.deleteChannelKingtags(hm);
    }
    @Override
    public void deleteRelations(List<Integer> channelIds, int kingtagId) throws Exception {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("channelIds", channelIds);
        hm.put("tagId", kingtagId);
        channelTagMapper.deleteKingtagChannels(hm);
    }
    @Override
    @Transactional
    public void updateRelations(int channelId, List<Integer> kingtagIds) throws Exception {
        List<kingtagDTO> originKingtags = selectSecondByFirst(channelId);
        List<Integer> originKingtagIds = new ArrayList<>();
        originKingtags.forEach(originKingtag->{
            originKingtagIds.add(originKingtag.id);
        });

        deleteRelations(channelId, originKingtagIds);
        insertRelations(channelId, kingtagIds);
    }
    @Override
    @Transactional
    public void updateRelations(List<Integer> channelIds, int kingtagId) throws Exception {
        List<channelDTO> originChannels = selectFirstBySecond(kingtagId);
        List<Integer> originChannelIds = new ArrayList<>();
        originChannels.forEach(originChannel->{
            originChannelIds.add(originChannel.id);
        });

        deleteRelations(originChannelIds, kingtagId);
        insertRelations(channelIds, kingtagId);
    }
}
