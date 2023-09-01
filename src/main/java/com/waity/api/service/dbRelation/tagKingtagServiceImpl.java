package com.waity.api.service.dbRelation;

import com.waity.api.domain.KingTag;
import com.waity.api.domain.Tag;
import com.waity.api.global.error.exception.ErrorCode;
import com.waity.api.global.error.exception.customRuntimeException;
import com.waity.api.mapper.tagMapper;
import com.waity.api.mapper.kingtagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class tagKingtagServiceImpl implements tagKingtagService {

    private tagMapper tagMapper;
    private kingtagMapper kingtagMapper;

    @Autowired
    public tagKingtagServiceImpl(kingtagMapper kingtagMapper) {
        this.kingtagMapper = kingtagMapper;
    }

    @Override
    public Tag selectFirstBySecond(int kingtagId) throws Exception {
        return Tag.createTag(kingtagMapper.selectTagByKingtag(kingtagId));
    }
    @Override
    public KingTag selectSecondByFirst(int tagId) throws Exception {
        KingTag kingtag = KingTag.createKingTag(kingtagMapper.selectKingtagByTag(tagId));
        kingtag.setParentTag(KingTag.createKingTag(kingtagMapper.selectParentTag(kingtag.getId())));
        return kingtag;
    }
    @Override
    public void insertRelation(int tagId, int kingtagId) throws Exception {
        throw new customRuntimeException(ErrorCode.INVALID_OPERATION);
    }
    @Override
    public void deleteRelation(int firstId, int secondId) throws Exception {
        throw new customRuntimeException(ErrorCode.INVALID_OPERATION);
    }
}
