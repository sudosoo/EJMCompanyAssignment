package com.example.assignment.service;

import com.example.assignment.dto.*;

import java.util.List;

public interface CommonCodeService {

    void createdCode(CreateCodeRequestDto requestDto);

    void createdGroup(CreateGroupRequestDto requestDto);

    CodeResponseDto searchCodeByKoreanNameOrCodeValue(SearchByKoreanNameOrCodeValueDto requestDto);
    List<CodeResponseDto> searchCodeListByGroupName(SearchCodeListByGroupNameDto requestDto);

    void updateGroupName(UpdateCodeRequestDto requestDto);
}
