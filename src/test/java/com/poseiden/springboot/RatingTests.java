package com.poseiden.springboot;

import com.poseiden.springboot.domain.Rating;
import com.poseiden.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RatingTests {

	@Autowired
	private RatingRepository ratingRepository;

	@Test
	public void ratingTest() {
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

		// Save
		rating = ratingRepository.save(rating);
		assertThat(rating.getId()).isNotNull();
		assertThat(rating.getOrderNumber()).isEqualTo(10);

		// Update
		rating.setOrderNumber(20);
		rating = ratingRepository.save(rating);
		assertThat(rating.getOrderNumber()).isEqualTo(20);

		// Find
		List<Rating> listResult = ratingRepository.findAll();
		assertThat(listResult.size()).isGreaterThan(0);

		// Delete
		Integer id = rating.getId();
		ratingRepository.delete(rating);
		Optional<Rating> ratingList = ratingRepository.findById(id);
		assertThat(ratingList.isPresent()).isFalse();
	}
}
