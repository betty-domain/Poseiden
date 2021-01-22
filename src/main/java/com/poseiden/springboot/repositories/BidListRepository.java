package com.poseiden.springboot.repositories;

import com.poseiden.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
