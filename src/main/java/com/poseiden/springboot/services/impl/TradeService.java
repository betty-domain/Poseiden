package com.poseiden.springboot.services.impl;

import com.poseiden.springboot.domain.Trade;
import com.poseiden.springboot.repositories.TradeRepository;
import com.poseiden.springboot.services.ITradeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger logger = LogManager.getLogger(TradeService.class);

    @Autowired
    TradeRepository tradeRepository;


    @Override
    public List<Trade> findAll() {
        logger.debug("call to findAll in TradeService");
        return tradeRepository.findAll();

    }

    @Override
    public Trade save(final Trade trade) {
        logger.debug("call to save in TradeService");
        return tradeRepository.save(trade);

    }

    @Override
    public Optional<Trade> findById(final Integer id) {
        logger.debug("call to findById: " + id + " in TradeService");
        return tradeRepository.findById(id);

    }

    @Override
    public void delete(final Trade trade) {
        logger.debug("call to delete in TradeService");
        tradeRepository.delete(trade);

    }
}
