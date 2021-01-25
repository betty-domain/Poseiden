package com.poseiden.springboot.repositories;

import com.poseiden.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
