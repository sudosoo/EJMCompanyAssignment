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


    public CreateCodeRequestDto(String codeValue, String koreaName) {
        this.codeValue = codeValue;
        this.koreaName = koreaName;
    }

}
