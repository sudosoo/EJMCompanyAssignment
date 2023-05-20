package com.example.assignment.repository;

import com.example.assignment.entity.CommonCode;
import com.example.assignment.entity.CodeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Long>, JpaSpecificationExecutor<CommonCode> {
    List<CommonCode> findByCodeGroup(CodeGroup codeGroup);
}
