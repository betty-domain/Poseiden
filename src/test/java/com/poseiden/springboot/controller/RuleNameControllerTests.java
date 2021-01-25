package com.poseiden.springboot.controller;

import com.poseiden.springboot.domain.RuleName;
import com.poseiden.springboot.services.IRuleNameService;
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
public class RuleNameControllerTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    IRuleNameService ruleNameService;

    @Test
    void get_ruleName_list_Ok() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/ruleName/list").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk())
                .andExpect(view().name("ruleName/list"));

        verify(ruleNameService, Mockito.times(1)).findAll();
    }

    @Test
    void get_ruleName_add_Form_Ok() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/ruleName/add").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("ruleName/add"));

        verify(ruleNameService, Mockito.times(0)).save(any());
    }

    @Test
    void post_ruleName_validate_ResultHasErrors() throws Exception {
        RuleName ruleName = new RuleName(null,"description","json","template","sqlStr","sqlPart");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/ruleName/validate").
                contentType(MediaType.APPLICATION_JSON).
                param("name",ruleName.getName()).
                param("description", ruleName.getDescription()).
                param("json", ruleName.getJson()).
                param("template",ruleName.getTemplate()).
                param("sqlStr", ruleName.getSqlStr()).
                param("sqlPart", ruleName.getSqlPart());

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("ruleName/add")).
                andExpect(model().hasErrors());

        verify(ruleNameService, Mockito.times(0)).save(any());
    }

    @Test
    void post_ruleName_validate_isOk_RedirectToBidList() throws Exception {
        RuleName ruleName = new RuleName("name","description","json","template","sqlStr","sqlPart");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/ruleName/validate").
                contentType(MediaType.APPLICATION_JSON).
                param("name",ruleName.getName()).
                param("description", ruleName.getDescription()).
                param("json", ruleName.getJson()).
                param("template",ruleName.getTemplate()).
                param("sqlStr", ruleName.getSqlStr()).
                param("sqlPart", ruleName.getSqlPart());


        when(ruleNameService.save(any())).thenReturn(ruleName);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/ruleName/list"));

        verify(ruleNameService, Mockito.times(1)).save(ruleName);
    }

    @Test
    void get_ruleName_updateForm_Ok() throws Exception {
        RuleName ruleName = new RuleName(null,"description","json","template","sqlStr","sqlPart");
        ruleName.setId(5);

        when(ruleNameService.findById(anyInt())).thenReturn(Optional.of(ruleName));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/ruleName/update/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("ruleName/update"));

        verify(ruleNameService, Mockito.times(0)).save(any());

    }

    @Test
    void get_ruleName_update_ThrowIllegalArgumentException() throws Exception {

        when(ruleNameService.findById(anyInt())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/ruleName/update/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        assertThatThrownBy(() -> mockMvc.perform(builder)).hasCause(new IllegalArgumentException("Invalid ruleName Id:5"));

    }

    @Test
    void post_ruleName_update_ResultHasErrors() throws Exception {
        RuleName ruleName = new RuleName("name","","json","template","sqlStr","sqlPart");
        ruleName.setId(5);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/ruleName/update/{id}",5).
                contentType(MediaType.APPLICATION_JSON).
                param("name",ruleName.getName()).
                param("description", ruleName.getDescription()).
                param("json", ruleName.getJson()).
                param("template",ruleName.getTemplate()).
                param("sqlStr", ruleName.getSqlStr()).
                param("sqlPart", ruleName.getSqlPart());


        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("ruleName/update")).
                andExpect(model().hasErrors());

        verify(ruleNameService, Mockito.times(0)).save(any());
    }

    @Test
    void post_ruleName_update_isOk_RedirectToBidList() throws Exception {
        RuleName ruleName = new RuleName("name","description","json","template","sqlStr","sqlPart");
        ruleName.setId(5);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/ruleName/update/{id}",5).
                contentType(MediaType.APPLICATION_JSON).
                param("name",ruleName.getName()).
                param("description", ruleName.getDescription()).
                param("json", ruleName.getJson()).
                param("template",ruleName.getTemplate()).
                param("sqlStr", ruleName.getSqlStr()).
                param("sqlPart", ruleName.getSqlPart());

        when(ruleNameService.save(any())).thenReturn(ruleName);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/ruleName/list"));

        verify(ruleNameService, Mockito.times(1)).save(ruleName);
    }

    @Test
    void get_ruleName_delete_Ok() throws Exception {
        RuleName ruleName = new RuleName("name","description","json","template","sqlStr","sqlPart");
        ruleName.setId(5);
        when(ruleNameService.findById(anyInt())).thenReturn(Optional.of(ruleName));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/ruleName/delete/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/ruleName/list"));

        verify(ruleNameService, Mockito.times(1)).delete(any());

    }

    @Test
    void get_ruleName_delete_ThrowIllegalArgumentException() throws Exception {

        when(ruleNameService.findById(anyInt())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/ruleName/delete/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        assertThatThrownBy(() -> mockMvc.perform(builder)).hasCause(new IllegalArgumentException("Invalid ruleName Id:5"));

    }
}
