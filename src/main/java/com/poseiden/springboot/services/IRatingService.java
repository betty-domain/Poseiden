package com.poseiden.springboot.services;

import com.poseiden.springboot.domain.Rating;

import java.util.List;
import java.util.Optional;

/**
 * Interface defining all things related to Rating
 */
public interface IRatingService {
    /**
     * Get all existing Rating
     * @return list of rating, can be empty
     */
    List<Rating> findAll();

    /**
     * add or update Rating entity
     * @param rating entity to save
     * @return saved Rating
     */
    Rating save(Rating rating);

    /**
     * Find Rating by Id
     * @param id id of Rating
     * @return Optional Rating
     */
    Optional<Rating> findById(Integer id);

    /**
     * Delete Rating
     * @param rating entity to delete
     */
    void delete(Rating rating);
}
