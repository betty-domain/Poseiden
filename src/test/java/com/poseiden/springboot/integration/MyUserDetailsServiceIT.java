package com.poseiden.springboot.integration;

import com.poseiden.springboot.domain.User;
import com.poseiden.springboot.repositories.UserRepository;
import com.poseiden.springboot.services.impl.MyUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MyUserDetailsServiceIT {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Test
    public void loadUserByUsernameNotFoundUser()
    {
        when(userRepository.findByUsernameIgnoreCase(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
                    userDetailsService.loadUserByUsername("myEmail");
                }
        );

        assertThat(exception.getMessage()).contains("User 404");
    }

    @Test
    public void loadUserByUsernameExistingUser()
    {
        User user = new User();
        user.setUsername("username");
        user.setPassword("myPassword");
        user.setRole("USER");
        when(userRepository.findByUsernameIgnoreCase(anyString())).thenReturn(Optional.of(user));

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        assertThat(userDetails.getUsername()).isEqualTo(user.getUsername());
        assertThat(userDetails.getPassword()).isEqualTo(user.getPassword());
        assertThat(userDetails.isEnabled()).isTrue();
        assertThat(userDetails.isAccountNonExpired()).isTrue();
        assertThat(userDetails.isAccountNonLocked()).isTrue();
        assertThat(userDetails.isCredentialsNonExpired()).isTrue();
        assertThat(userDetails.getAuthorities().contains("USER"));
    }
}
