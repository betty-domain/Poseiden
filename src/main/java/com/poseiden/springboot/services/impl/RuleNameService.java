package com.poseiden.springboot.services.impl;

import com.poseiden.springboot.domain.RuleName;
import com.poseiden.springboot.repositories.RuleNameRepository;
import com.poseiden.springboot.services.IRuleNameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger logger = LogManager.getLogger(RuleNameService.class);

    @Autowired
    RuleNameRepository ruleNameRepository;

    @Override
    public List<RuleName> findAll() {
        logger.debug("call to findAll in RuleNameService");
        return ruleNameRepository.findAll();
    }

    @Override
    public RuleName save(final RuleName ruleName) {
        logger.debug("call to save in RuleNameService");
        return ruleNameRepository.save(ruleName);
    }

    @Override
    public Optional<RuleName> findById(final Integer id) {
        logger.debug("call to findById: " + id + " in RuleNameService");
        return ruleNameRepository.findById(id);
    }

    @Override
    public void delete(final RuleName ruleName) {
        logger.debug("call to delete in RuleNameService");
        ruleNameRepository.delete(ruleName);
    }
}
