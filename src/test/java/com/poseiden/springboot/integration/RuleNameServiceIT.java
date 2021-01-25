package com.poseiden.springboot.integration;

import com.poseiden.springboot.domain.RuleName;
import com.poseiden.springboot.services.IRuleNameService;
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
public class RuleNameServiceIT {
    @Autowired
    IRuleNameService ruleNameService;

    @Test
    void saveRuleName()
    {
        RuleName ruleName = new RuleName("name","description","json","template","sqlStr","sqlPart");

        // Save
        ruleName = ruleNameService.save(ruleName);
        assertThat(ruleName.getId()).isNotNull();
        assertThat(ruleName.getName()).isEqualTo("name");
    }

    @Test
    void updateRuleName()
    {
        RuleName ruleName = new RuleName();
        ruleName.setId(1);

        // Update
        ruleName.setDescription("modified description");
        ruleName = ruleNameService.save(ruleName);
        assertThat(ruleName.getDescription()).isEqualTo("modified description");

    }

    @Test
    void findAll()
    {
        // Find
        List<RuleName> listResult = ruleNameService.findAll();
        assertThat(listResult.size()).isGreaterThan(0);

    }

    @Test
    void deleteRuleName() {
        RuleName ruleName = new RuleName();
        ruleName.setId(2);

        // Delete
        Integer id = ruleName.getId();
        ruleNameService.delete(ruleName);
        Optional<RuleName> bidList = ruleNameService.findById(id);
        assertThat(bidList.isPresent()).isFalse();
    }
}
