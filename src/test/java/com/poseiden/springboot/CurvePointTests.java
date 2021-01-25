package com.poseiden.springboot;

import com.poseiden.springboot.domain.CurvePoint;
import com.poseiden.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CurvePointTests {

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Test
	public void curvePointTest() {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);

		// Save
		curvePoint = curvePointRepository.save(curvePoint);
		assertThat(curvePoint.getId()).isNotNull();
		assertThat(curvePoint.getCurveId()).isEqualTo(10);

		// Update
		curvePoint.setCurveId(20);
		curvePoint = curvePointRepository.save(curvePoint);
		assertThat(curvePoint.getCurveId()).isEqualTo(20);

		// Find
		List<CurvePoint> listResult = curvePointRepository.findAll();
		assertThat(listResult.size()).isGreaterThan(0);

		// Delete
		Integer id = curvePoint.getId();
		curvePointRepository.delete(curvePoint);
		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
		assertThat(curvePointList.isPresent()).isFalse();
	}

}
