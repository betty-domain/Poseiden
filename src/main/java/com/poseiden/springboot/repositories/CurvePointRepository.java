package com.poseiden.springboot.repositories;

import com.poseiden.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
