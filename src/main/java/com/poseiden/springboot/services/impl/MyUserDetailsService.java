package com.poseiden.springboot.services.impl;

import com.poseiden.springboot.domain.User;
import com.poseiden.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Custom MyUserDetailsService used to authenticate user with user in database
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * load user in DB by username
     * @param username username of searched user
     * @return found user
     * @throws UsernameNotFoundException if user is not found
     */
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameIgnoreCase(username);

        if (!user.isPresent())
        {
            throw new UsernameNotFoundException("User 404");
        }

        return user.get();
    }
}
