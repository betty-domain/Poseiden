package com.poseiden.springboot.controller;

import com.poseiden.springboot.domain.BidList;
import com.poseiden.springboot.repositories.BidListRepository;
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
class BidListControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BidListRepository bidListRepository;

    @Test
    void get_BidList_list_Ok() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/bidList/list").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk())
                .andExpect(view().name("bidList/list"));

        verify(bidListRepository, Mockito.times(1)).findAll();
    }

    @Test
    void get_bidlist_add_Form_Ok() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/bidList/add").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("bidList/add"));

        verify(bidListRepository, Mockito.times(0)).save(any());
    }

    @Test
    void post_bidlist_validate_ResultHasErrors() throws Exception {
        BidList bidList = new BidList();
        bidList.setBid(20d);
        bidList.setAccount(null);
        bidList.setType("myType");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/bidList/validate").
                contentType(MediaType.APPLICATION_JSON).
                param("account",bidList.getAccount()).
                param("type",bidList.getType()).
                param("bid",bidList.getBid().toString());

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("bidList/add")).
                andExpect(model().hasErrors());

        verify(bidListRepository, Mockito.times(0)).save(any());
    }

    @Test
    void post_bidlist_validate_isOk_RedirectToBidList() throws Exception {
        BidList bidList = new BidList();
        bidList.setBid(20d);
        bidList.setAccount("myAccount");
        bidList.setType("myType");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/bidList/validate").
                contentType(MediaType.APPLICATION_JSON).
                param("account",bidList.getAccount()).
                param("type",bidList.getType()).
                param("bid",bidList.getBid().toString());

        when(bidListRepository.save(any())).thenReturn(bidList);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/bidList/list"));
        ;

        verify(bidListRepository, Mockito.times(1)).save(bidList);
    }

    @Test
    void get_bidlist_updateForm_Ok() throws Exception
    {
        BidList bidList = new BidList();
        bidList.setBid(20d);
        bidList.setAccount("myAccount");
        bidList.setType("myType");

        when(bidListRepository.findById(anyInt())).thenReturn(Optional.of(bidList));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/bidList/update/{id}",5).
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("bidList/update"));

        verify(bidListRepository, Mockito.times(0)).save(any());

    }

    @Test
    void get_bidlist_update_ThrowIllegalArgumentException() throws Exception
    {

        when(bidListRepository.findById(anyInt())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/bidList/update/{id}",5).
                contentType(MediaType.APPLICATION_JSON);

        assertThatThrownBy(() -> mockMvc.perform(builder)).hasCause(new IllegalArgumentException("Invalid bidList Id:5"));

    }

    @Test
    void post_bidlist_update_ResultHasErrors() throws Exception {
        BidList bidList = new BidList();
        bidList.setBid(20d);
        bidList.setAccount(null);
        bidList.setType("myType");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/bidList/update/{id}",5).
                contentType(MediaType.APPLICATION_JSON).
                param("account",bidList.getAccount()).
                param("type",bidList.getType()).
                param("bid",bidList.getBid().toString());

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("bidList/update")).
                andExpect(model().hasErrors());

        verify(bidListRepository, Mockito.times(0)).save(any());
    }

    @Test
    void post_bidlist_update_isOk_RedirectToBidList() throws Exception {
        BidList bidList = new BidList();
        bidList.setBid(20d);
        bidList.setAccount("myAccount");
        bidList.setType("myType");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/bidList/update/{id}", 5).
                contentType(MediaType.APPLICATION_JSON).
                param("account",bidList.getAccount()).
                param("type",bidList.getType()).
                param("bid",bidList.getBid().toString());

        when(bidListRepository.save(any())).thenReturn(bidList);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/bidList/list"));


        verify(bidListRepository, Mockito.times(1)).save(bidList);
    }

    @Test
    void get_bidlist_delete_Ok() throws Exception
    {
        BidList bidList = new BidList();
        bidList.setBid(20d);
        bidList.setAccount("myAccount");
        bidList.setType("myType");

        when(bidListRepository.findById(anyInt())).thenReturn(Optional.of(bidList));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/bidList/delete/{id}",5).
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/bidList/list"));

        verify(bidListRepository, Mockito.times(1)).delete(any());

    }

    @Test
    void get_bidlist_delete_ThrowIllegalArgumentException() throws Exception
    {

        when(bidListRepository.findById(anyInt())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/bidList/delete/{id}",5).
                contentType(MediaType.APPLICATION_JSON);

        assertThatThrownBy(() -> mockMvc.perform(builder)).hasCause(new IllegalArgumentException("Invalid bidList Id:5"));


    }
}
