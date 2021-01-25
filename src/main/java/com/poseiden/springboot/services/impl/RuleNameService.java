package com.poseiden.springboot.services.impl;

import com.poseiden.springboot.domain.RuleName;
import com.poseiden.springboot.services.IRuleNameService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of interface IRuleNameService
 */
@Transactional
@Service
public class RuleNameService implements IRuleNameService {
    @Override
    public List<RuleName> findAll() {
        return null;
    }

    @Override
    public RuleName save(final RuleName ruleName) {
        return null;
    }

    @Override
    public Optional<RuleName> findById(final Integer id) {
        return Optional.empty();
    }

    @Override
    public void delete(final RuleName rating) {

    }
}
