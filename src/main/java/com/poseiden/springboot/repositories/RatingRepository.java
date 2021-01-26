package com.poseiden.springboot.repositories;

import com.poseiden.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository for interaction between Rating entity and associated table
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
