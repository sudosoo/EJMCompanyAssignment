package com.example.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
public class SearchByKoreanNameOrCodeValueDto {
    @Nullable
    private String codeValue;
    @Nullable
    private String koreaName;

}
