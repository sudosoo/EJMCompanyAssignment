package com.example.assignment.controller;

import com.example.assignment.dto.*;
import com.example.assignment.service.CommonCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommonCodeController {

    private final CommonCodeService commonCodeService;

    @PostMapping("/code")
    public ResponseEntity<Void> createdCode(@Param("codeValue") String codeValue,
                                                   @Param("koreanName") String koreanName,
                                                   @Param("groupName") String groupName) {
        CreateCodeRequestDto requestDto;
        if (groupName.isEmpty()) {
            requestDto = new CreateCodeRequestDto(codeValue, koreanName);
        } else {
            requestDto = new CreateCodeRequestDto(codeValue, koreanName, groupName);
        }
        commonCodeService.createdCode(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/group")
    public ResponseEntity<Void> createdGroup(@Param("groupName") String groupName) {
        CreateGroupRequestDto requestDto = new CreateGroupRequestDto(groupName);
        commonCodeService.createdGroup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/group")
    public ResponseEntity<Void> updateGroupByCodeName(@Param("codeValue") String codeValue,
                                                      @Param("koreaName") String koreaName,
                                                      @Param("groupName") String groupName) {
        UpdateCodeRequestDto requestDto;
        if ( koreaName == "null" || koreaName.isEmpty()) {
            requestDto = UpdateCodeRequestDto.builder()
                    .codeValue(codeValue)
                    .groupName(groupName).build();
        } else if (codeValue == "null" || codeValue.isEmpty()){
            requestDto = UpdateCodeRequestDto.builder()
                    .koreaName(koreaName)
                    .groupName(groupName).build() ;
        } else {
            requestDto = UpdateCodeRequestDto.builder()
                    .codeValue(codeValue)
                    .koreaName(koreaName)
                    .groupName(groupName).build() ;
        }
        commonCodeService.updateGroupName(requestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/code")
    public ResponseEntity<CodeResponseDto> searchByKoreanNameOrCodeValue(
                                        @Param("codeValue") String codeValue,
                                        @Param("koreanName") String koreanName) {
        var requestDto = new SearchByKoreanNameOrCodeValueDto(codeValue, koreanName);
        return ResponseEntity.ok(commonCodeService.searchCodeByKoreanNameOrCodeValue(requestDto));
    }

    @GetMapping("/group")
    public ResponseEntity<List<CodeResponseDto>> SearchCodeListByGroupName(
                                        @Param("groupName") String groupName) {
        var requestDto = new SearchCodeListByGroupNameDto(groupName);
        return ResponseEntity.ok(commonCodeService.searchCodeListByGroupName(requestDto));
    }


}
