package com.poseiden.springboot.controller;

import com.poseiden.springboot.domain.CurvePoint;
import com.poseiden.springboot.services.ICurvePointService;
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
public class CurvePointControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ICurvePointService curvePointService;

    @Test
    void get_curvePoint_list_Ok() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/curvePoint/list").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk())
                .andExpect(view().name("curvePoint/list"));

        verify(curvePointService, Mockito.times(1)).findAll();
    }

    @Test
    void get_curvePoint_add_Form_Ok() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/curvePoint/add").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("curvePoint/add"));

        verify(curvePointService, Mockito.times(0)).save(any());
    }

    @Test
    void post_curvePoint_validate_ResultHasErrors() throws Exception {
        CurvePoint curvePoint = new CurvePoint(null,36.5,26.5);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/curvePoint/validate").
                contentType(MediaType.APPLICATION_JSON).
                param("curveId", "").
                param("value", curvePoint.getValue().toString()).
                param("term", curvePoint.getTerm().toString());

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("curvePoint/add")).
                andExpect(model().hasErrors());

        verify(curvePointService, Mockito.times(0)).save(any());
    }

    @Test
    void post_curvePoint_validate_isOk_RedirectToBidList() throws Exception {
        CurvePoint curvePoint = new CurvePoint(20,36.5,26.5);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/curvePoint/validate").
                contentType(MediaType.APPLICATION_JSON).
                param("curveId", curvePoint.getCurveId().toString()).
                param("value", curvePoint.getValue().toString()).
                param("term", curvePoint.getTerm().toString());

        when(curvePointService.save(any())).thenReturn(curvePoint);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/curvePoint/list"));
        ;

        verify(curvePointService, Mockito.times(1)).save(curvePoint);
    }

    @Test
    void get_curvePoint_updateForm_Ok() throws Exception {
        CurvePoint curvePoint = new CurvePoint(20,36.5,26.5);
        when(curvePointService.findById(anyInt())).thenReturn(Optional.of(curvePoint));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/curvePoint/update/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("curvePoint/update"));

        verify(curvePointService, Mockito.times(0)).save(any());

    }

    @Test
    void get_curvePoint_update_ThrowIllegalArgumentException() throws Exception {

        when(curvePointService.findById(anyInt())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/curvePoint/update/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        assertThatThrownBy(() -> mockMvc.perform(builder)).hasCause(new IllegalArgumentException("Invalid curvePoint Id:5"));

    }

    @Test
    void post_curvePoint_update_ResultHasErrors() throws Exception {
        CurvePoint curvePoint = new CurvePoint(null,36.5,26.5);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/curvePoint/update/{id}", 5).
                contentType(MediaType.APPLICATION_JSON).
                param("curveId", "").
                param("value", curvePoint.getValue().toString()).
                param("term", curvePoint.getTerm().toString());

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("curvePoint/update")).
                andExpect(model().hasErrors());

        verify(curvePointService, Mockito.times(0)).save(any());
    }

    @Test
    void post_curvePoint_update_isOk_RedirectToBidList() throws Exception {
        CurvePoint curvePoint = new CurvePoint(20,36.5,26.5);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/curvePoint/update/{id}", 5).
                contentType(MediaType.APPLICATION_JSON).
                param("curveId",curvePoint.getCurveId().toString()).
                param("value",curvePoint.getValue().toString()).
                param("term",curvePoint.getTerm().toString());
        when(curvePointService.save(any())).thenReturn(curvePoint);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/curvePoint/list"));

        verify(curvePointService, Mockito.times(1)).save(curvePoint);
    }

    @Test
    void get_curvePoint_delete_Ok() throws Exception {
        CurvePoint curvePoint = new CurvePoint(20,36.5,26.5);

        when(curvePointService.findById(anyInt())).thenReturn(Optional.of(curvePoint));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/curvePoint/delete/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/curvePoint/list"));

        verify(curvePointService, Mockito.times(1)).delete(any());

    }

    @Test
    void get_curvePoint_delete_ThrowIllegalArgumentException() throws Exception {

        when(curvePointService.findById(anyInt())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/curvePoint/delete/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        assertThatThrownBy(() -> mockMvc.perform(builder)).hasCause(new IllegalArgumentException("Invalid curvePoint Id:5"));

    }
}


