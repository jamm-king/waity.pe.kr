package com.waity.api.domain;

import com.waity.api.dto.kingtagDTO;
import com.waity.api.dto.tagDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class KingTag extends Tag {

    private KingTag parentTag;

    public KingTag(kingtagDTO kingtagDTO) {
        super(kingtagDTO);
        this.parentTag = new KingTag();
    }
    // static
    public static KingTag createKingTag(kingtagDTO kingtagDTO) {
        return new KingTag(kingtagDTO);
    }
    public static List<KingTag> createKingTags(List<kingtagDTO> kingtagDTOs) {
        List<KingTag> kingtags = new ArrayList<>();
        for(kingtagDTO kingtagDTO : kingtagDTOs) {
            kingtags.add(createKingTag(kingtagDTO));
        }
        return kingtags;
    }
    public static kingtagDTO toKingtagDTO(KingTag kingtag) {
        return new kingtagDTO(
                kingtag.getId(),
                kingtag.getTagName(),
                kingtag.getParentTagId()
        );
    }
    public static List<kingtagDTO> toKingtagDTOs(List<KingTag> kingtags) {
        List<kingtagDTO> kingtagDTOs = new ArrayList<>();
        for(KingTag kingtag : kingtags) {
            kingtagDTOs.add(toKingtagDTO(kingtag));
        }
        return kingtagDTOs;
    }
    // parentTag
    public int getParentTagId() {
        return parentTag.getId();
    }
    public void setParentTag(KingTag kingtag) {
        this.parentTag = kingtag;
    }
}
