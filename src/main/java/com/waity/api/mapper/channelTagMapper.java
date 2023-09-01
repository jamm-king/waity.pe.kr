package com.waity.api.mapper;

import com.waity.api.dto.channelDTO;
import com.waity.api.dto.kingtagDTO;
import com.waity.api.dto.tagDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface channelTagMapper {
    List<channelDTO> selectChannelsByTag(int tagId) throws Exception;
    List<tagDTO> selectTagsByChannel(int channelId) throws Exception;
    void insertChannelTag(HashMap<String, Integer> hm) throws Exception;
    void insertChannelTags(HashMap<String, Object> hm) throws Exception;
    void insertTagChannels(HashMap<String, Object> hm) throws Exception;
    void deleteChannelTag(HashMap<String, Integer> hm) throws Exception;
    void deleteChannelTags(HashMap<String, Object> hm) throws Exception;
    void deleteTagChannels(HashMap<String, Object> hm) throws Exception;

    List<channelDTO> selectChannelsByKingtag(int id) throws Exception;
    List<kingtagDTO> selectKingtagsByChannel(int channelId) throws Exception;
    void insertChannelKingtag(HashMap<String, Integer> hm) throws Exception;
    void insertChannelKingtags(HashMap<String, Object> hm) throws Exception;
    void insertKingtagChannels(HashMap<String, Object> hm) throws Exception;
    void deleteChannelKingtag(HashMap<String, Integer> hm) throws Exception;
    void deleteChannelKingtags(HashMap<String, Object> hm) throws Exception;
    void deleteKingtagChannels(HashMap<String, Object> hm) throws Exception;
}
