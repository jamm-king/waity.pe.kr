package com.waity.api.service;

import com.waity.api.dto.tagDTO;
import com.waity.api.mapper.tagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class normaltagServiceImpl implements normaltagService {

    @Autowired
    private tagMapper tagMapper;

    @Autowired
    public normaltagServiceImpl(tagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }
    @Override
    public List<tagDTO> selectEntityAll() throws Exception {
        return tagMapper.selectTagAll();
    }
    @Override
    public tagDTO selectEntityById(int id) throws Exception {
        return tagMapper.selectTagById(id);
    }
    @Override
    public List<tagDTO> selectEntitiesByIds(List<Integer> ids) throws Exception {
        return tagMapper.selectTagsByIds(ids);
    }
    @Override
    public void insertEntity(tagDTO tag) throws Exception {
        tagMapper.insertTag(tag);
    }
    @Override
    public void insertEntities(List<tagDTO> tags) throws Exception {
        tagMapper.insertTags(tags);
    }
    @Override
    public void updateEntity(tagDTO tag) throws Exception {
        tagMapper.updateTag(tag);
    }
    @Override
    public void updateEntities(List<tagDTO> tags) throws Exception {
        tagMapper.updateTags(tags);
    }
    @Override
    public void deleteEntity(int id) throws Exception {
        tagMapper.deleteTag(id);
    }
    @Override
    public void deleteEntities(List<Integer> ids) throws Exception {
        tagMapper.deleteTags(ids);
    }
}
