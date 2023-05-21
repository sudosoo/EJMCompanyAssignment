package com.example.assignment.service;

import com.example.assignment.dto.*;
import com.example.assignment.entity.CommonCode;
import com.example.assignment.entity.CodeGroup;
import com.example.assignment.repository.CommonCodeRepository;
import com.example.assignment.repository.CommonCodeSpecification;
import com.example.assignment.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommonCodeServiceImpl implements CommonCodeService {
    private final CommonCodeRepository commonCodeRepository;
    private final GroupRepository groupRepository;

    @Override
    public void createdCode(CreateCodeRequestDto requestDto) {
        CommonCode commonCode = CommonCode.buildEntityFromDto(requestDto.getCodeValue(), requestDto.getKoreaName());
        commonCodeRepository.save(commonCode);
    }


    @Override
    public void createdGroup(CreateGroupRequestDto requestDto) {
        CodeGroup group = getAndCreateGroupByGroupName(requestDto.getGroupName());
        groupRepository.save(group);
    }

    @Override
    public void updateGroupName(UpdateCodeRequestDto requestDto) {
        String groupName = requestDto.getGroupName();
        CodeGroup codeGroup = groupRepository.findGroupByGroupName(groupName)
                .orElseThrow(() -> new IllegalArgumentException("해당 그룹이 존재하지 않습니다: " + groupName));
        CommonCode code = getEntity(requestDto.getKoreaName(), requestDto.getCodeValue());
        code.setCodeGroup(codeGroup);
        commonCodeRepository.save(code);
    }

    @Override
    @Transactional(readOnly = true)
    public CodeResponseDto searchCodeByKoreanNameOrCodeValue(SearchByKoreanNameOrCodeValueDto searchDto) {
        CommonCode code = getEntity(searchDto.getKoreaName(), searchDto.getCodeValue());
        return CodeResponseDto.toDto(code);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CodeResponseDto> searchCodeListByGroupName(SearchCodeListByGroupNameDto searchDto) {
        CodeGroup codeGroup = groupRepository.findGroupByGroupName(searchDto.getGroupName())
                .orElseThrow(() -> new IllegalArgumentException("해당 그룹이 존재하지 않습니다: " + searchDto.getGroupName()));
        return commonCodeRepository.findByCodeGroup(codeGroup).stream()
                .map(CodeResponseDto::toDto)
                .collect(Collectors.toList());
    }

    private CommonCode getEntity(String koreaName, String codeValue) {
        CommonCode code = commonCodeRepository.findOne(
                        Specification
                                .where(CommonCodeSpecification.hasKoreaName(koreaName))
                                .and(CommonCodeSpecification.hasCodeValue(codeValue)))
                .orElseThrow(() -> new IllegalArgumentException("해당 코드는 존재 하지 않습니다: "+koreaName+codeValue));
        return code;
    }

    private CodeGroup getAndCreateGroupByGroupName(String groupName) {
        return groupRepository.findGroupByGroupName(groupName)
                .orElseGet(() -> CodeGroup.buildEntityFromDto(groupName));
    }

    public CommonCode getEntityTestMethod(String koreaName, String codeValue) {
        return getEntity(koreaName, codeValue);
    }

    public CodeGroup getAndCreateGroupByGroupNameTestMethod(String groupName) {
        return getAndCreateGroupByGroupName(groupName);
    }
}