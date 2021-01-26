package com.poseiden.springboot.repositories;

import com.poseiden.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository for interaction between Trade entity and associated table
 */
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
