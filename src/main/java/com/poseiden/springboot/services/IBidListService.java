package com.poseiden.springboot.services;

import com.poseiden.springboot.domain.BidList;

import java.util.List;
import java.util.Optional;

/**
 * Interface defining all things related to BidList
 */
public interface IBidListService {
    /**
     * Get All existing BidList
     * @return List of bidList, can be empty
     */
    List<BidList> findAll();

    /**
     * Add or update a bidList
     * @param bid entity to save
     * @return Saved BidList
     */
    BidList save(BidList bid);

    /**
     * Get bidList by id
     * @param id id of BidList
     * @return optional bidList
     */
    Optional<BidList> findById(Integer id);

    /**
     * Delete bidList
     * @param bidList entity to delete
     */
    void delete(BidList bidList);
}
