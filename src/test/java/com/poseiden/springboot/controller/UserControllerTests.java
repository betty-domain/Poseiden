package com.poseiden.springboot.controller;

import com.poseiden.springboot.domain.User;
import com.poseiden.springboot.services.IUserService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IUserService userService;

    @Test
    void get_user_list_Ok() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/user/list").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk())
                .andExpect(view().name("user/list"));

        verify(userService, Mockito.times(1)).findAll();
    }

    @Test
    void get_user_add_Form_Ok() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/user/add").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("user/add"));

        verify(userService, Mockito.times(0)).save(any());
    }

    @Test
    void post_user_validate_ResultHasErrors() throws Exception {
        User user = new User(null,"password", "fullname","ROLE");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/user/validate").
                contentType(MediaType.APPLICATION_JSON).
                param("username",user.getUsername()).
                param("password", user.getPassword()).
                param("fullname", user.getFullname().toString()).
                param("role",user.getRole());

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("user/add")).
                andExpect(model().hasErrors());

        verify(userService, Mockito.times(0)).save(any());
    }

    @Test
    void post_user_validate_isOk_RedirectToBidList() throws Exception {
        User user = new User("username","password", "fullname","ROLE");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/user/validate").
                contentType(MediaType.APPLICATION_JSON).
                param("username",user.getUsername()).
                param("password", user.getPassword()).
                param("fullname", user.getFullname().toString()).
                param("role",user.getRole());

        when(userService.save(any())).thenReturn(user);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/user/list"));

        verify(userService, Mockito.times(1)).save(any());
    }

    @Test
    void get_user_updateForm_Ok() throws Exception {
        User user = new User("username","password", "fullname","ROLE");

        when(userService.findById(anyInt())).thenReturn(Optional.of(user));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/user/update/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("user/update"));

        verify(userService, Mockito.times(0)).save(any());

    }

    @Test
    void get_user_update_ThrowIllegalArgumentException() throws Exception {

        when(userService.findById(anyInt())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/user/update/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        assertThatThrownBy(() -> mockMvc.perform(builder)).hasCause(new IllegalArgumentException("Invalid user Id:5"));

    }

    @Test
    void post_user_update_ResultHasErrors() throws Exception {
        User user = new User("username",null, "fullname","ROLE");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/user/update/{id}",5).
                contentType(MediaType.APPLICATION_JSON).
                param("username",user.getUsername()).
                param("password", user.getPassword()).
                param("fullname", user.getFullname().toString()).
                param("role",user.getRole());


        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(view().name("user/update")).
                andExpect(model().hasErrors());

        verify(userService, Mockito.times(0)).save(any());
    }

    @Test
    void post_user_update_isOk_RedirectToBidList() throws Exception {

        User user = new User("username","password", "fullname","ROLE");
        user.setId(5);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/user/update/{id}",5).
                contentType(MediaType.APPLICATION_JSON).
                param("username",user.getUsername()).
                param("password", user.getPassword()).
                param("fullname", user.getFullname().toString()).
                param("role",user.getRole());

        when(userService.save(any())).thenReturn(user);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/user/list"));

        verify(userService, Mockito.times(1)).save(any());
    }

    @Test
    void get_user_delete_Ok() throws Exception {
        User user = new User("username","password", "fullname","ROLE");
        user.setId(5);

        when(userService.findById(anyInt())).thenReturn(Optional.of(user));
        when(userService.findById(anyInt())).thenReturn(Optional.of(user));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/user/delete/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(redirectedUrl("/user/list"));

        verify(userService, Mockito.times(1)).delete(any());

    }

    @Test
    void get_user_delete_ThrowIllegalArgumentException() throws Exception {

        when(userService.findById(anyInt())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/user/delete/{id}", 5).
                contentType(MediaType.APPLICATION_JSON);

        assertThatThrownBy(() -> mockMvc.perform(builder)).hasCause(new IllegalArgumentException("Invalid user Id:5"));

    }
}


