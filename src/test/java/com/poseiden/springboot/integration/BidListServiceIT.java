package com.poseiden.springboot.integration;

import com.poseiden.springboot.domain.BidList;
import com.poseiden.springboot.services.IBidListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:insert_TestData.sql")
})
public class BidListServiceIT {

    @Autowired
    IBidListService bidListService;

    @Test
    void saveBidList()
    {
        BidList bid = new BidList();
        bid.setAccount("Account Test");
        bid.setType("Type Test");
        bid.setBidQuantity(10d);

        // Save
        bid = bidListService.save(bid);
        assertThat(bid.getBidListId()).isNotNull();
        assertThat(bid.getBidQuantity()).isEqualTo(10d);
    }

    @Test
    void updateBidList()
    {
        BidList bid = new BidList();
        bid.setBidListId(1);

        // Update
        bid.setBidQuantity(20d);
        bid = bidListService.save(bid);
        assertThat(bid.getBidQuantity()).isEqualTo(20d);

    }

    @Test
    void findAll()
    {
        // Find
        List<BidList> listResult = bidListService.findAll();
        assertThat(listResult.size()).isGreaterThan(0);

    }

    @Test
    void deleteBidList() {
        BidList bid = new BidList();
        bid.setBidListId(2);

        // Delete
        Integer id = bid.getBidListId();
        bidListService.delete(bid);
        Optional<BidList> bidList = bidListService.findById(id);
        assertThat(bidList.isPresent()).isFalse();
    }


}
