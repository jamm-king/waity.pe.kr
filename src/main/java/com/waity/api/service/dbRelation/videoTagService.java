package com.waity.api.service.dbRelation;

import com.waity.api.domain.Tag;
import com.waity.api.domain.Video;

public interface videoTagService extends manyToManyService<Video, Tag> {

}
