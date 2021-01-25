package com.poseiden.springboot.services;

import com.poseiden.springboot.domain.RuleName;

import java.util.List;
import java.util.Optional;

/**
 * Interface defining all things related to RuleName
 */
public interface IRuleNameService {
    /**
     * get all existing ruleName
     * @return list of rulename, can be empty
     */
    List<RuleName> findAll();

    /**
     * add or update ruleName
     * @param ruleName entity to save
     * @return saved ruleName
     */
    RuleName save(RuleName ruleName);

    /**
     * Find RuleName by Id
     * @param id id of RuleName
     * @return Optional RuleName
     */
    Optional<RuleName> findById(Integer id);

    /**
     * Delete RuleName
     * @param rating entity to delete
     */
    void delete(RuleName rating);
}
