package com.poseiden.springboot.services.impl;

import com.poseiden.springboot.domain.BidList;
import com.poseiden.springboot.repositories.BidListRepository;
import com.poseiden.springboot.services.IBidListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of interface IBidListService
 */
@Transactional
@Service
public class BidListService implements IBidListService {

    private static final Logger logger = LogManager.getLogger(BidListService.class);

    @Autowired
    BidListRepository bidListRepository;

    @Override
    public List<BidList> findAll() {
        logger.debug("call to findAll in BidListService");
        return bidListRepository.findAll();
    }

    @Override
    public BidList save(final BidList bid) {
        logger.debug("call to save in BidListService");
        return bidListRepository.save(bid);
    }

    @Override
    public Optional<BidList> findById(final Integer id) {
        logger.debug("call to findById: " + id + " in BidListService");
        return bidListRepository.findById(id);
    }

    @Override
    public void delete(final BidList bidList) {
        logger.debug("call to delete in BidListService");
        bidListRepository.delete(bidList);
    }
}
