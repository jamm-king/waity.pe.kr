package com.waity.api.dto;

import com.waity.api.domain.KingTag;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class kingtagDTO extends tagDTO{
    public int parentTagId;

    public kingtagDTO(int id, String tagName, int parentTagId) {
        super(id, tagName);
        this.parentTagId = parentTagId;
    }
    public kingtagDTO(tagDTO tagDTO, int parentTagId) {
        super(tagDTO.getId(), tagDTO.getTagName());
        this.parentTagId = parentTagId;
    }
}