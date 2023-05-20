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

    /**
     * 코드의 벨류값과 한국어 이름을 넣으면 공용코드를 생성해주는 API입니다.
     * 그룹은 만들때 지정할 수도 있고, 그룹이 존재하지 않는다면 자동으로 그룹을 생성해서 지정 해줍니다.
     *
     * @param codeValue @NotNull 코드의 벨류값을 지정합니다
     * @param koreaName @NotNull 코드의 한국어이름을 지정합니다
     * @param groupName @Nullable 코드의 그룹을 지정합니다
     * @return "200"
     * */
    @PostMapping("/code")
    public ResponseEntity<Void> createdCode(@Param("codeValue") String codeValue,
                                                   @Param("koreaName") String koreaName,
                                                   @Param("groupName") String groupName) {
        CreateCodeRequestDto requestDto;
        if (groupName.isEmpty()) {
            requestDto = new CreateCodeRequestDto(codeValue, koreaName);
        } else {
            requestDto = new CreateCodeRequestDto(codeValue, koreaName, groupName);
        }
        commonCodeService.createdCode(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 그룹이름을 넣으면 해당 그룹을 생성해주는 API입니다.
     *
     * @param groupName @NotNull 코드의 그룹을 지정합니다
     * @return "200"
     * */
    @PostMapping("/group")
    public ResponseEntity<Void> createdGroup(@Param("groupName") String groupName) {
        CreateGroupRequestDto requestDto = new CreateGroupRequestDto(groupName);
        commonCodeService.createdGroup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 이미 생성된 코드의 그룹을 지정 / 변경 합니다.
     * 매개변수는 둘중 선택 가능하며 두개 다 사용도 가능한 동적 API입니다.
     *
     * @param codeValue @Nullable 코드의 벨류값을 검색합니다
     * @param koreaName @Nullable 코드의 한국어이름을 검색합니다
     * @param groupName @NotNull 코드의 그룹을 지정합니다
     * @return "200"
     * */
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

    /**
     * 코드 검색 API입니다.
     * 코드의 벨류값 또는 한국어 이름을 넣어 검색을 수행합니다.
     *
     * @param codeValue @Nullable 코드의 벨류값을 검색합니다
     * @param koreaName @Nullable 코드의 한국어이름을 검색합니다
     * @return CodeResponseDto
     * */
    @GetMapping("/code")
    public ResponseEntity<CodeResponseDto> searchByKoreanNameOrCodeValue(
                                        @Param("codeValue") String codeValue,
                                        @Param("koreanName") String koreaName) {
        var requestDto = new SearchByKoreanNameOrCodeValueDto(codeValue, koreaName);
        return ResponseEntity.ok(commonCodeService.searchCodeByKoreanNameOrCodeValue(requestDto));
    }

    /**
     * 그룹단위 검색 API입니다.
     * 그룹명을 넣어 그룹에 속해있는 코드를 검색을 수행합니다.
     *
     * @param groupName @NotNull 그룹이름을 검색합니다
     * @return List<CodeResponseDto>
     * */
    @GetMapping("/group")
    public ResponseEntity<List<CodeResponseDto>> searchCodeListByGroupName(
                                        @Param("groupName") String groupName) {
        var requestDto = new SearchCodeListByGroupNameDto(groupName);
        return ResponseEntity.ok(commonCodeService.searchCodeListByGroupName(requestDto));
    }
}
