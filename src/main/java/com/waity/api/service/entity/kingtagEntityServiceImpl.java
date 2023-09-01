package com.waity.api.service.entity;

import com.waity.api.domain.KingTag;
import com.waity.api.dto.kingtagDTO;
import com.waity.api.service.db.dbService;
import com.waity.api.service.db.kingtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class kingtagEntityServiceImpl implements kingtagEntityService {

    private dbService<KingTag> kingtagService;

    @Autowired
    public kingtagEntityServiceImpl(kingtagService kingtagService) {
        this.kingtagService = kingtagService;
    }

    @Override
    public List<kingtagDTO> getAllKingtags() throws Exception {
        List<KingTag> kingtags = kingtagService.selectEntityAll();
        List<kingtagDTO> kingtagDTOs = KingTag.toKingtagDTOs(kingtags);
        return kingtagDTOs;
    }
    @Override
    public void createKingtag(kingtagDTO kingtagDTO) throws Exception {
        KingTag kingtag = KingTag.createKingTag(kingtagDTO);
        kingtagService.insertEntity(kingtag);
    }
    @Override
    public void updateKingtag(kingtagDTO kingtagDTO) throws Exception {
        KingTag kingtag = kingtagService.selectEntityById(kingtagDTO.getId());
        KingTag parentTag = kingtagService.selectEntityById(kingtagDTO.getParentTagId());
        kingtag.update(kingtagDTO);
        kingtag.setParentTag(parentTag);
        kingtagService.updateEntity(kingtag);
    }
    @Override
    public void deleteKingtag(int id) throws Exception {
        kingtagService.deleteEntity(id);
    }
}
