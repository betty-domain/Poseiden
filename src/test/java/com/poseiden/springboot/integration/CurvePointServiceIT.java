package com.poseiden.springboot.integration;

import com.poseiden.springboot.domain.CurvePoint;
import com.poseiden.springboot.services.ICurvePointService;
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
public class CurvePointServiceIT {
    @Autowired
    ICurvePointService curvePointService;

    @Test
    void saveCurvePoint()
    {
        CurvePoint curvePoint = new CurvePoint(10,15d,25d);

        // Save
        curvePoint = curvePointService.save(curvePoint);
        assertThat(curvePoint.getId()).isNotNull();
        assertThat(curvePoint.getValue()).isEqualTo(25d);
    }

    @Test
    void updateCurvePoint()
    {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);

        // Update
        curvePoint.setCurveId(50);
        curvePoint = curvePointService.save(curvePoint);
        assertThat(curvePoint.getCurveId()).isEqualTo(50);

    }

    @Test
    void findAll()
    {
        // Find
        List<CurvePoint> listResult = curvePointService.findAll();
        assertThat(listResult.size()).isGreaterThan(0);

    }

    @Test
    void deleteCurvePoint() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(2);

        // Delete
        Integer id = curvePoint.getId();
        curvePointService.delete(curvePoint);
        Optional<CurvePoint> bidList = curvePointService.findById(id);
        assertThat(bidList.isPresent()).isFalse();
    }
}
