package com.poseiden.springboot.services.impl;

import com.poseiden.springboot.domain.Rating;
import com.poseiden.springboot.repositories.RatingRepository;
import com.poseiden.springboot.services.IRatingService;
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

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public List<Rating> findAll() {

        return ratingRepository.findAll();
    }

    @Override
    public Rating save(final Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Optional<Rating> findById(final Integer id) {
        return ratingRepository.findById(id);
    }

    @Override
    public void delete(final Rating rating) {
        ratingRepository.delete(rating);
    }
}
