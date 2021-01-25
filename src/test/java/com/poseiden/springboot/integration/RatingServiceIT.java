package com.poseiden.springboot.integration;

import com.poseiden.springboot.domain.Rating;
import com.poseiden.springboot.services.IRatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:insert_TestData.sql")
})
public class RatingServiceIT {
    @Autowired
    IRatingService ratingService;

    @Test
    void saveRating()
    {
        Rating rating = new Rating("moody","sand","fitch",10);

        // Save
        rating = ratingService.save(rating);
        assertThat(rating.getId()).isNotNull();
        assertThat(rating.getFitchRating()).isEqualTo("fitch");
    }

    @Test
    void updateRating()
    {
        Rating rating = new Rating();
        rating.setId(1);

        // Update
        rating.setOrderNumber(50);
        rating = ratingService.save(rating);
        assertThat(rating.getOrderNumber()).isEqualTo(50);

    }

    @Test
    void findAll()
    {
        // Find
        List<Rating> listResult = ratingService.findAll();
        assertThat(listResult.size()).isGreaterThan(0);

    }

    @Test
    void deleteRating() {
        Rating rating = new Rating();
        rating.setId(2);

        // Delete
        Integer id = rating.getId();
        ratingService.delete(rating);
        Optional<Rating> bidList = ratingService.findById(id);
        assertThat(bidList.isPresent()).isFalse();
    }
}
