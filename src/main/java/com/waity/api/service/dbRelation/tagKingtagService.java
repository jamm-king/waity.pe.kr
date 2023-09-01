package com.waity.api.service.dbRelation;

import com.waity.api.domain.Tag;
import com.waity.api.domain.KingTag;

public interface tagKingtagService extends oneToOneService<Tag, KingTag> {
}
