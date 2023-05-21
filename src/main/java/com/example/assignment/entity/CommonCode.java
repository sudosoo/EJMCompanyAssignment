package com.example.assignment.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String codeValue;
    @Column(unique = true)
    private String koreaName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codegroup_id")
    private CodeGroup codeGroup;

    private CommonCode(String codeValue, String koreanName) {
        this.codeValue = codeValue;
        this.koreaName = koreanName;
    }
    public static CommonCode buildEntityFromDto(String codeValue, String koreanName){
        return new CommonCode(codeValue,koreanName);
    }
    public void setCodeGroup(CodeGroup codeGroup){
        if (this.codeGroup != null) {
            this.codeGroup.getCodeList().remove(this);
        }
        this.codeGroup = codeGroup;
        codeGroup.getCodeList().add(this);
    }

}
