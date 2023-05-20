package com.example.assignment.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateGroupRequestDto {
    @NotNull
    private String groupName;

}
