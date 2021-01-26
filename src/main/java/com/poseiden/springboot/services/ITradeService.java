package com.poseiden.springboot.services;

import com.poseiden.springboot.domain.Trade;

import java.util.List;
import java.util.Optional;

/**
 * Interface defining all things related to Trade
 */
public interface ITradeService {

    /**
     * get all existing trade
     * @return list of rulename, can be empty
     */
    List<Trade> findAll();

    /**
     * add or update trade
     * @param trade entity to save
     * @return saved trade
     */
    Trade save(Trade trade);

    /**
     * Find Trade by Id
     * @param id id of Trade
     * @return Optional Trade
     */
    Optional<Trade> findById(Integer id);

    /**
     * Delete Trade
     * @param trade entity to delete
     */
    void delete(Trade trade);
}
