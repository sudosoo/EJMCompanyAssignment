package com.example.assignment.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
public class SearchCodeListByGroupNameDto {
    @NotNull
    private String groupName;

}
