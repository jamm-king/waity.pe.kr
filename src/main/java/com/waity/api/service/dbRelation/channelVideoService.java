package com.waity.api.service.dbRelation;

import com.waity.api.domain.Channel;
import com.waity.api.domain.Video;

public interface channelVideoService extends oneToManyService<Channel, Video> {

}
