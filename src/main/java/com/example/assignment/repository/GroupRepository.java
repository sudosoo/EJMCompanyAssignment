package com.example.assignment.repository;

import com.example.assignment.entity.CodeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<CodeGroup, Long> {
    Optional<CodeGroup> findGroupByGroupName(String groupName);

}
