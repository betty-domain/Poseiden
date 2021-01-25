package com.poseiden.springboot.controller;

import com.poseiden.springboot.domain.Rating;
import com.poseiden.springboot.services.IRatingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class RatingControllerTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    IRatingService ratingService;

    @Test
    void get_rating_list_Ok() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/rating/list").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk())
                .andExpect(view().name("rating/list"));

        verify(ratingService, Mockito.times(1)).findAll();
    }

    @Test
    void get_rating_add_Form_Ok() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/rating/add").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("rating/add"));

        verify(ratingService, Mockito.times(0)).save(any());
    }

    @Test
    void post_rating_validate_ResultHasErrors() throws Exception {
        Rating rating = new Rating("moody","sandRating","",5);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/rating/validate").
                contentType(MediaType.APPLICATION_JSON).
                param("moodyRating",rating.getMoodyRating()).
                param("sandRating", rating.getSandRating()).
                param("fitchRating", rating.getFitchRating()).
                param("orderNumber",rating.getOrderNumber().toString());

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("rating/add")).
                andExpect(model().hasErrors());

        verify(ratingService, Mockito.times(0)).save(any());
    }

    @Test
    void post_rating_validate_isOk_RedirectToBidList() throws Exception {
        Rating rating = new Rating("moody","sandRating","fitchRating",5);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/rating/validate").
                contentType(MediaType.APPLICATION_JSON).
                param("moodyRating",rating.getMoodyRating()).
                param("sandRating", rating.getSandRating()).
                param("fitchRating", rating.getFitchRating()).
                param("orderNumber",rating.getOrderNumber().toString());


        when(ratingService.save(any())).thenReturn(rating);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/rating/list"));

        verify(ratingService, Mockito.times(1)).save(rating);
    }

    @Test
    void get_rating_updateForm_Ok() throws Exception {
        Rating rating = new Rating("moody","sandRating","fitchRating",5);

        when(ratingService.findById(anyInt())).thenReturn(Optional.of(rating));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/rating/update/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("rating/update"));

        verify(ratingService, Mockito.times(0)).save(any());

    }

    @Test
    void get_rating_update_ThrowIllegalArgumentException() throws Exception {

        when(ratingService.findById(anyInt())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/rating/update/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        assertThatThrownBy(() -> mockMvc.perform(builder)).hasCause(new IllegalArgumentException("Invalid rating Id:5"));

    }

    @Test
    void post_rating_update_ResultHasErrors() throws Exception {
        Rating rating = new Rating("moody","sandRating","fitchRating",null);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/rating/update/{id}").
                contentType(MediaType.APPLICATION_JSON).
                param("moodyRating",rating.getMoodyRating()).
                param("sandRating", rating.getSandRating()).
                param("fitchRating", rating.getFitchRating()).
                param("orderNumber","");

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("rating/update")).
                andExpect(model().hasErrors());

        verify(ratingService, Mockito.times(0)).save(any());
    }

    @Test
    void post_rating_update_isOk_RedirectToBidList() throws Exception {
        Rating rating = new Rating("moody","sandRating","fitchRating",5);
        rating.setId(5);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/rating/update/{id}", 5).
                contentType(MediaType.APPLICATION_JSON).
                param("moodyRating",rating.getMoodyRating()).
                param("sandRating", rating.getSandRating()).
                param("fitchRating", rating.getFitchRating()).
                param("orderNumber",rating.getOrderNumber().toString());
        when(ratingService.save(any())).thenReturn(rating);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/rating/list"));

        verify(ratingService, Mockito.times(1)).save(rating);
    }

    @Test
    void get_rating_delete_Ok() throws Exception {
        Rating rating = new Rating("moody","sandRating","fitchRating",5);

        when(ratingService.findById(anyInt())).thenReturn(Optional.of(rating));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/rating/delete/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/rating/list"));

        verify(ratingService, Mockito.times(1)).delete(any());

    }

    @Test
    void get_rating_delete_ThrowIllegalArgumentException() throws Exception {

        when(ratingService.findById(anyInt())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/rating/delete/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        assertThatThrownBy(() -> mockMvc.perform(builder)).hasCause(new IllegalArgumentException("Invalid rating Id:5"));

    }
}

