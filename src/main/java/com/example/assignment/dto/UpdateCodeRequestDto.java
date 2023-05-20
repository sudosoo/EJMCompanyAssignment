package com.example.assignment.dto;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateCodeRequestDto {
    @Nullable
    private String codeValue;
    @Nullable
    private String koreaName;
    @NotNull
    private String groupName;

    @Builder
    private UpdateCodeRequestDto(@Nullable String codeValue, @Nullable String koreaName, String groupName) {
        this.codeValue = codeValue;
        this.koreaName = koreaName;
        this.groupName = groupName;
    }


}
