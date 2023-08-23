package com.waity.api.mapper;

import com.waity.api.dto.channelDTO;
import com.waity.api.dto.tagDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface channelTagMapper {
    List<channelDTO> selectChannelsByTag(int id) throws Exception;
    List<channelDTO> selectChannelsByTagNames(String[] tags) throws Exception;
    List<tagDTO> selectTagByChannel(int channelId) throws Exception;
    void insertChannelTags(HashMap<String, Object> hm) throws Exception;
    void deleteChannelTags(HashMap<String, Object> hm) throws Exception;

    List<channelDTO> selectChannelsByKingtag(int id) throws Exception;
    List<tagDTO> selectKingtagByChannel(int channelId) throws Exception;
    void insertChannelKingtags(HashMap<String, Object> hm) throws Exception;
    void deleteChannelKingtags(HashMap<String, Object> hm) throws Exception;
}
