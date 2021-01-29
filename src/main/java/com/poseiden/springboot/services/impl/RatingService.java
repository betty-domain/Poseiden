package com.poseiden.springboot.services.impl;

import com.poseiden.springboot.domain.Rating;
import com.poseiden.springboot.repositories.RatingRepository;
import com.poseiden.springboot.services.IRatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of interface IRatingService
 */
@Transactional
@Service
public class RatingService implements IRatingService {

    private static final Logger logger = LogManager.getLogger(RatingService.class);

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public List<Rating> findAll() {
        logger.debug("call to findAll in RatingService");
        return ratingRepository.findAll();
    }

    @Override
    public Rating save(final Rating rating) {
        logger.debug("call to save in RatingService");
        return ratingRepository.save(rating);
    }

    @Override
    public Optional<Rating> findById(final Integer id) {
        logger.debug("call to findById: " + id + " in RatingService");
        return ratingRepository.findById(id);
    }

    @Override
    public void delete(final Rating rating) {
        logger.debug("call to delete in RatingService");
        ratingRepository.delete(rating);
    }
}
