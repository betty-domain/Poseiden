package com.poseiden.springboot.services.impl;

import com.poseiden.springboot.domain.CurvePoint;
import com.poseiden.springboot.repositories.CurvePointRepository;
import com.poseiden.springboot.services.ICurvePointService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of interface ICurvePointService
 */
@Transactional
@Service
public class CurvePointService implements ICurvePointService {

    private static final Logger logger = LogManager.getLogger(CurvePointService.class);

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Override
    public List<CurvePoint> findAll() {
        logger.debug("call to findAll in CurvePointService");
        return curvePointRepository.findAll();
    }

    @Override
    public CurvePoint save(final CurvePoint curvePoint) {
        logger.debug("call to save in CurvePointService");
        return curvePointRepository.save(curvePoint);
    }

    @Override
    public Optional<CurvePoint> findById(final Integer id) {
        logger.debug("call to findById: " + id + " in CurvePointService");
        return curvePointRepository.findById(id);
    }

    @Override
    public void delete(final CurvePoint curvePoint) {
        logger.debug("call to delete in CurvePointService");
        curvePointRepository.delete(curvePoint);
    }
}
