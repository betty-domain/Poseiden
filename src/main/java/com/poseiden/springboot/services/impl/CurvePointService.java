package com.poseiden.springboot.services.impl;

import com.poseiden.springboot.domain.CurvePoint;
import com.poseiden.springboot.repositories.CurvePointRepository;
import com.poseiden.springboot.services.ICurvePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of interface ICurvePointService
 */
@Transactional
@Service
public class CurvePointService implements ICurvePointService {
    @Autowired
    private CurvePointRepository curvePointRepository;

    @Override
    public List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }

    @Override
    public CurvePoint save(final CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    @Override
    public Optional<CurvePoint> findById(final Integer id) {
        return Optional.empty();
    }

    @Override
    public void delete(final CurvePoint curvePoint) {

    }
}
