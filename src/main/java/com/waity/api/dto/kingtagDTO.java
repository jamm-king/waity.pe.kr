package com.waity.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class kingtagDTO extends tagDTO{
    public int parentTagId;
}