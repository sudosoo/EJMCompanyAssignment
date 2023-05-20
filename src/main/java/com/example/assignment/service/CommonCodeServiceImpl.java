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

    //코드를 만들땐 필드값을 필수로 넣어준다 그룹은 필수 x 그룹을
    public void createdCode(CreateCodeRequestDto requestDto) {
        String groupName = requestDto.getGroupName();
        CommonCode commonCode = CommonCode.buildEntityFromDto(requestDto.getCodeValue(), requestDto.getKoreaName());
        if (groupName != null && !groupName.isEmpty()) {
            CodeGroup codeGroup = groupRepository.findGroupByGroupName(groupName)
                    .orElseGet(() -> CreateAndGetGroup(groupName)
                    );
            commonCode.setCodeGroup(codeGroup);
        }
        commonCodeRepository.save(commonCode);
    }

    public void createdGroup(CreateGroupRequestDto requestDto) {
        var groupName = requestDto.getGroupName();
        CreateAndGetGroup(groupName);
    }


    @Override
    public void updateGroupName(UpdateCodeRequestDto requestDto) {
        String groupName = requestDto.getGroupName();
        CodeGroup codeGroup = groupRepository.findGroupByGroupName(groupName)
                .orElseThrow(() -> new IllegalArgumentException("해당 그룹이 존재하지 않습니다: " + groupName));
        String codeValue = requestDto.getCodeValue();
        String koreaName = requestDto.getKoreaName();
        CommonCode code = getEntity(koreaName, codeValue);
        code.setCodeGroup(codeGroup);

        commonCodeRepository.save(code);
    }

    @Transactional(readOnly = true)
    public CodeResponseDto searchCodeByKoreanNameOrCodeValue(SearchByKoreanNameOrCodeValueDto searchDto) {
        String codeValue = searchDto.getCodeValue();
        String koreaName = searchDto.getKoreaName();
        CommonCode code = getEntity(koreaName, codeValue);
        if (code == null) {
            throw new IllegalArgumentException("해당 코드는 존재하지 않습니다.");
        }
        return CodeResponseDto.toDto(code);
    }

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
                .orElseThrow(()->new IllegalArgumentException("해당 코드는 존재 하지 않습니다."));
        return code;
    }

    private CodeGroup CreateAndGetGroup(String groupName) {
        CodeGroup codeGroup = CodeGroup.buildEntityFromDto(groupName);
        return groupRepository.save(codeGroup);
    }
}