package com.intellect37.api_test.repository;

import com.intellect37.api_test.model.HelloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloRepository extends JpaRepository<HelloEntity, Long> {
}
