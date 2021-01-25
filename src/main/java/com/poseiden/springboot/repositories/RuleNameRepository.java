package com.poseiden.springboot.repositories;

import com.poseiden.springboot.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
}
