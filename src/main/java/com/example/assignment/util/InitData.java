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

    }
}