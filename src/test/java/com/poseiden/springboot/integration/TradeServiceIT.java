package com.poseiden.springboot.integration;

import com.poseiden.springboot.domain.Trade;
import com.poseiden.springboot.services.ITradeService;
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
public class TradeServiceIT {
    @Autowired
    ITradeService tradeService;

    @Test
    void saveTrade()
    {
        Trade trade = new Trade("account","type");

        // Save
        trade = tradeService.save(trade);
        assertThat(trade.getTradeId()).isNotNull();
        assertThat(trade.getAccount()).isEqualTo("account");
    }

    @Test
    void updateTrade()
    {
        Trade trade = new Trade();
        trade.setTradeId(1);

        // Update
        trade.setDealName("deal name");
        trade = tradeService.save(trade);
        assertThat(trade.getDealName()).isEqualTo("deal name");

    }

    @Test
    void findAll()
    {
        // Find
        List<Trade> listResult = tradeService.findAll();
        assertThat(listResult.size()).isGreaterThan(0);

    }

    @Test
    void deleteTrade() {
        Trade trade = new Trade();
        trade.setTradeId(2);

        // Delete
        Integer id = trade.getTradeId();
        tradeService.delete(trade);
        Optional<Trade> bidList = tradeService.findById(id);
        assertThat(bidList.isPresent()).isFalse();
    }
}
