package com.poseiden.springboot.repositories;

import com.poseiden.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * JpaRepository for interaction between User entity and associated table
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * find user by username ignoring case
     * @param username username of searched user
     * @return Optional user
     */
    Optional<User> findByUsernameIgnoreCase(String username);
}
