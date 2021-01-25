package com.poseiden.springboot.services;

import com.poseiden.springboot.domain.CurvePoint;

import java.util.List;
import java.util.Optional;

/**
 * Interface defining all things related to CurvePoint
 */
public interface ICurvePointService {
    /**
     * Get All existing CurvePoint
     * @return list of curvePoint, can be empty
     */
    List<CurvePoint> findAll();

    /**
     * Add or update a curvePoint
     * @param curvePoint entity to save
     * @return Saved CurvePoint
     */
    CurvePoint save(CurvePoint curvePoint);

    /**
     * Get curvePoint by id
     * @param id id of curvePoint
     * @return optional curvePoint
     */
    Optional<CurvePoint> findById(Integer id);

    /**
     * Delete curvePoint
     * @param curvePoint entity to delete
     */
    void delete(CurvePoint curvePoint);
}
