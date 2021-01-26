package com.poseiden.springboot.services.impl;

import com.poseiden.springboot.domain.Trade;
import com.poseiden.springboot.services.ITradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of interface ITradeService
 */
@Transactional
@Service
public class TradeService implements ITradeService {
    @Override
    public List<Trade> findAll() {
        return null;
    }

    @Override
    public Trade save(final Trade trade) {
        return null;
    }

    @Override
    public Optional<Trade> findById(final Integer id) {
        return Optional.empty();
    }

    @Override
    public void delete(final Trade trade) {

    }
}
