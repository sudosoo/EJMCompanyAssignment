package com.example.assignment.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class CreateCodeRequestDto {
    @NotNull
    private String codeValue;
    @NotNull
    private String koreaName;
    @Nullable
    private String groupName;

    public CreateCodeRequestDto(String codeValue, String koreaName) {
        this.codeValue = codeValue;
        this.koreaName = koreaName;
    }
    public CreateCodeRequestDto(String codeValue, String koreaName, String groupName) {
        this.codeValue = codeValue;
        this.koreaName = koreaName;
        this.groupName = groupName;
    }

}
