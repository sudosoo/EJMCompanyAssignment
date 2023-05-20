package com.example.assignment.util;

import com.example.assignment.entity.CommonCode;
import com.example.assignment.entity.CodeGroup;
import com.example.assignment.repository.CommonCodeRepository;
import com.example.assignment.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitData implements ApplicationRunner {

    private final CommonCodeRepository commonCodeRepository;
    private final GroupRepository groupRepository;


    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
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
}