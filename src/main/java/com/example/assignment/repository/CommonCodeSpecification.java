package com.example.assignment.repository;

import com.example.assignment.entity.CommonCode;
import org.springframework.data.jpa.domain.Specification;

public class CommonCodeSpecification{
    public static Specification<CommonCode> hasKoreaName(String koreaName) {
        return (root, query, criteriaBuilder) -> koreaName == null ? null : criteriaBuilder.equal(root.get("koreaName"), koreaName);
    }

    public static Specification<CommonCode> hasCodeValue(String codeValue) {
        return (root, query, criteriaBuilder) -> codeValue == null ? null : criteriaBuilder.equal(root.get("codeValue"), codeValue);
    }
}
