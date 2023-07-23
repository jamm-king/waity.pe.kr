package com.waity.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class channelDTO {
	public int id;
	public String channelId;
	public String keyword;
	public String image;
	public String title;
	public long subscriptionCount;
	public long viewCount;
	public String description;
	public int videoCount;
}
