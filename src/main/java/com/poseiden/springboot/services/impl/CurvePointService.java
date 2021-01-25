package com.poseiden.springboot.services.impl;

import com.poseiden.springboot.domain.CurvePoint;
import com.poseiden.springboot.services.ICurvePointService;
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
    @Override
    public List<CurvePoint> findAll() {
        return null;
    }

    @Override
    public CurvePoint save(final CurvePoint curvePoint) {
        return null;
    }

    @Override
    public Optional<CurvePoint> findById(final Integer id) {
        return Optional.empty();
    }

    @Override
    public void delete(final CurvePoint curvePoint) {

    }
}
