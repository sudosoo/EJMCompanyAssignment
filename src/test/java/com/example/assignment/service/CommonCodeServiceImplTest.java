package com.example.assignment.service;

import com.example.assignment.dto.*;
import com.example.assignment.entity.CodeGroup;
import com.example.assignment.entity.CommonCode;
import com.example.assignment.repository.CommonCodeRepository;
import com.example.assignment.repository.GroupRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class CommonCodeServiceImplTest {
    @Autowired
    private CommonCodeServiceImpl commonCodeService;
    @Autowired
    private CommonCodeRepository commonCodeRepository;
    @Autowired
    private GroupRepository groupRepository;

    @BeforeEach
    public void setup() {
        var code1 = CommonCode.buildEntityFromDto("123", "잭");
        var code2 = CommonCode.buildEntityFromDto("234", "리빈");
        var code3 = CommonCode.buildEntityFromDto("345", "킴");
        var code4 = CommonCode.buildEntityFromDto("567", "힐");
        var code5 = CommonCode.buildEntityFromDto("678", "던");

        var group1 = CodeGroup.buildEntityFromDto("100");
        var group2 = CodeGroup.buildEntityFromDto("200");
        var group3 = CodeGroup.buildEntityFromDto("300");

        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);

        code1.setCodeGroup(group1);
        code2.setCodeGroup(group2);
        code3.setCodeGroup(group3);
        code4.setCodeGroup(group1);
        code5.setCodeGroup(group2);

        commonCodeRepository.save(code1);
        commonCodeRepository.save(code2);
        commonCodeRepository.save(code3);
        commonCodeRepository.save(code4);
        commonCodeRepository.save(code5);
    }

    @Test
    @DisplayName("코드 만들기")
    public void testCreatedCode() {
        CreateCodeRequestDto testDto = new CreateCodeRequestDto("789", "빅");
        var createdCode = CommonCode.buildEntityFromDto(testDto.getCodeValue(),testDto.getKoreaName());

        Assertions.assertEquals(createdCode.getCodeValue(),"789");
    }

    @Test
    @DisplayName("그룹 만들기")
    public void testCreatedGroup() {
        CreateGroupRequestDto dto = new CreateGroupRequestDto("100");
        var saveGroup = commonCodeService.getAndCreateGroupByGroupNameTestMethod(dto.getGroupName());

        assertNotNull(saveGroup.getId());
    }

    @Test
    @DisplayName("그룹 재 지정하기")
    public void testUpdateGroupName() {
        UpdateCodeRequestDto dto1 = UpdateCodeRequestDto.builder()
                .groupName("100")
                .codeValue("123")
                .build();
        var testCode = CommonCode.buildEntityFromDto("123", "잭");

        CodeGroup codeGroup = CodeGroup.buildEntityFromDto("300");
        Optional<CodeGroup> codeGroupTest = groupRepository.findGroupByGroupName(dto1.getGroupName());

        CommonCode resultCode = commonCodeService.getEntityTestMethod(null,dto1.getCodeValue());
        resultCode.setCodeGroup(codeGroup);
        CommonCode saveCode = commonCodeRepository.save(resultCode);

        Assertions.assertEquals(resultCode.getCodeValue(),testCode.getCodeValue());
        Assertions.assertEquals(codeGroupTest.get().getGroupName(),"100");
        Assertions.assertEquals(resultCode.getCodeGroup().getGroupName(),"300");
        assertNotNull(saveCode.getId());
    }

    @Test
    @DisplayName("코드 이름 또는 벨류로 해당 코드 찾기")
    public void testSearchCodeByKoreanNameOrCodeValue() {
        SearchByKoreanNameOrCodeValueDto testDto = new SearchByKoreanNameOrCodeValueDto("123", null);
        SearchByKoreanNameOrCodeValueDto testDto2 = new SearchByKoreanNameOrCodeValueDto(null, "잭");
        SearchByKoreanNameOrCodeValueDto testDto3 = new SearchByKoreanNameOrCodeValueDto("123", "잭");

        var getCode = commonCodeService.searchCodeByKoreanNameOrCodeValue(testDto);
        var getCode2 = commonCodeService.searchCodeByKoreanNameOrCodeValue(testDto2);
        var getCode3 = commonCodeService.searchCodeByKoreanNameOrCodeValue(testDto3);

        assertEquals(getCode.getKoreaName(),getCode2.getKoreaName(),getCode3.getKoreaName());
    }

    @Test
    @DisplayName("그룹이름으로 해당 그룹에 속해있는 코드 찾기")
    public void testSearchCodeListByGroupName() {
        SearchCodeListByGroupNameDto testDto = new SearchCodeListByGroupNameDto("100");

        Optional<CodeGroup> codeGroup = groupRepository.findGroupByGroupName(testDto.getGroupName());
        List<CodeResponseDto> results = commonCodeService.searchCodeListByGroupName(testDto);

        assertEquals(codeGroup.get().getGroupName(),"100");
        assertEquals(2, results.size());
        assertEquals("잭", results.get(0).getKoreaName());
        assertEquals("123", results.get(0).getCodeValue());
        assertEquals("힐", results.get(1).getKoreaName());
        assertEquals("567", results.get(1).getCodeValue());
    }

}