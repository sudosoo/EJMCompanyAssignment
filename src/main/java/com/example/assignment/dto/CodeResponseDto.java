package com.example.assignment.dto;

import com.example.assignment.entity.CommonCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodeResponseDto {
    private String codeValue;
    private String koreaName;
    private String groupName;

    private CodeResponseDto(String codeValue, String koreaName, String groupName) {
        this.codeValue = codeValue;
        this.koreaName = koreaName;
        this.groupName = groupName;
    }
    public static CodeResponseDto toDto(CommonCode code){
        return new CodeResponseDto(code.getCodeValue(),code.getKoreaName(),code.getCodeGroup().getGroupName());
    }
}
