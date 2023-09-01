package com.waity.api.mapper;

import com.waity.api.dto.tagDTO;
import com.waity.api.dto.videoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface videoTagMapper {
    List<videoDTO> selectVideosByTag(int tagId) throws Exception;
    List<tagDTO> selectTagsByVideo(int videoId) throws Exception;
    void insertVideoTag(HashMap<String, Integer> hm) throws Exception;
    void insertVideoTags(HashMap<String, Object> hm) throws Exception;
    void insertTagVideos(HashMap<String, Object> hm) throws Exception;
    void deleteVideoTag(HashMap<String, Integer> hm) throws Exception;
    void deleteVideoTags(HashMap<String, Object> hm) throws Exception;
    void deleteTagVideos(HashMap<String, Object> hm) throws Exception;
}
