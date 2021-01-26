package com.poseiden.springboot.services;

import com.poseiden.springboot.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface defining all things related to User
 */
public interface IUserService {
    /**
     * get all existing user
     * @return list of user, can be empty
     */
    List<User> findAll();

    /**
     * add or update user
     * @param user entity to save
     * @return saved user
     */
    User save(User user);

    /**
     * Find User by Id
     * @param id id of User
     * @return Optional User
     */
    Optional<User> findById(Integer id);

    /**
     * Delete User
     * @param user entity to delete
     */
    void delete(User user);
}
