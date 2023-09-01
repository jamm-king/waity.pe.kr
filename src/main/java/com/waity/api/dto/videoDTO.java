package com.waity.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class videoDTO {
	public int id;
	public String videoId;
	public String title;
	public String thumbnail;
	public int channelId;
}