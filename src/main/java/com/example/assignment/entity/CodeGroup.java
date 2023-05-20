package com.example.assignment.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodeGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String groupName;
    @OneToMany(mappedBy = "codeGroup")
    private List<CommonCode> codeList = new ArrayList<>();

    private CodeGroup(String groupName) {
        this.groupName = groupName;
    }

    public static CodeGroup buildEntityFromDto(String groupName){
        return new CodeGroup(groupName);
    }

}
