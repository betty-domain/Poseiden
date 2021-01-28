package com.poseiden.springboot.integration;

import com.poseiden.springboot.domain.User;
import com.poseiden.springboot.services.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:insert_TestData.sql")
})
public class UserServiceIT {
    @Autowired
    IUserService userService;

    @Test
    void saveUser()
    {
        User user = new User("username","Password1@","fullname","USER");

        // Save
        user = userService.save(user);
        assertThat(user.getId()).isNotNull();
        assertThat(user.getUsername()).isEqualTo("username");
    }

    @Test
    void updateUser()
    {
        User user = new User("username","password","fullname","USER");
        user.setId(1);

        // Update
        user.setFullname("modified fullname");
        user = userService.save(user);
        assertThat(user.getFullname()).isEqualTo("modified fullname");

    }

    @Test
    void findAll()
    {
        // Find
        List<User> listResult = userService.findAll();
        assertThat(listResult.size()).isGreaterThan(0);

    }

    @Test
    void deleteUser() {
        User user = new User();
        user.setId(2);

        // Delete
        Integer id = user.getId();
        userService.delete(user);
        Optional<User> bidList = userService.findById(id);
        assertThat(bidList.isPresent()).isFalse();
    }
}


