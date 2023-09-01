package com.waity.api.service.entity;

import com.waity.api.dto.kingtagDTO;

import java.util.List;

public interface kingtagEntityService {

    List<kingtagDTO> getAllKingtags() throws Exception;

    void createKingtag(kingtagDTO kingtag) throws Exception;

    void updateKingtag(kingtagDTO kingtag) throws Exception;

    void deleteKingtag(int id) throws Exception;
}
