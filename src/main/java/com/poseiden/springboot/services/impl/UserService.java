package com.poseiden.springboot.services.impl;

import com.poseiden.springboot.domain.User;
import com.poseiden.springboot.repositories.UserRepository;
import com.poseiden.springboot.services.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of interface IUserService
 */
@Transactional
@Service
public class UserService implements IUserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;


    @Override
    public List<User> findAll() {
        logger.debug("call to findAll in UserService");
        return userRepository.findAll();

    }

    @Override
    public User save(final User user) {
        logger.debug("call to save in UserService");
        return userRepository.save(user);

    }

    @Override
    public Optional<User> findById(final Integer id) {
        logger.debug("call to findById: " + id + " in UserService");
        return userRepository.findById(id);

    }

    @Override
    public void delete(final User user) {
        logger.debug("call to delete in UserService");
        userRepository.delete(user);

    }
}
