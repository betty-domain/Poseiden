package com.poseiden.springboot.repositories;

import com.poseiden.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository for interaction between BidList entity and associated table
 */
public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
