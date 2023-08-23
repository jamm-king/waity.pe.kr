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
    public List<tagDTO> selectTagAll() throws Exception {
        return tagMapper.selectTagAll();
    }
    @Override
    public tagDTO selectTagById(int id) throws Exception {
        return tagMapper.selectTagById(id);
    }
    @Override
    public List<tagDTO> selectTagsByIds(List<Integer> ids) throws Exception {
        return tagMapper.selectTagByIds(ids);
    }
    @Override
    public void insertTag(tagDTO tag) throws Exception {
        tagMapper.insertTag(tag);
    }
    @Override
    public void updateTag(tagDTO tag) throws Exception {
        tagMapper.updateTag(tag);
    }
    @Override
    public void deleteTag(int id) throws Exception {
        tagMapper.deleteTag(id);
    }
}
