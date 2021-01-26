package com.poseiden.springboot.repositories;

import com.poseiden.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository for interaction between CurvePoint entity and associated table
 */
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
