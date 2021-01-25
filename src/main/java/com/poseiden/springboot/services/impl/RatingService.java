package com.poseiden.springboot.services.impl;

import com.poseiden.springboot.domain.Rating;
import com.poseiden.springboot.services.IRatingService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of interface IRatingService
 */
@Transactional
@Service
public class RatingService implements IRatingService {
    @Override
    public List<Rating> findAll() {
        return null;
    }

    @Override
    public Rating save(final Rating rating) {
        return null;
    }

    @Override
    public Optional<Rating> findById(final Integer id) {
        return Optional.empty();
    }

    @Override
    public void delete(final Rating rating) {

    }
}
