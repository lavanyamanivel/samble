package com.example.lms.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.lms.dao.ResultsDao;
import com.example.lms.entity.Results;
import com.example.lms.entity.Student;

@Service
public class ResultsService {
	@Autowired
	ResultsDao resdao;
	@Autowired
	RestTemplate rest;

	public Results getTotal(Results a) {
		String url1 = "http://localhost:8080/studentDetails/getB/";
		ResponseEntity<Student> response = rest.exchange(url1 + a.getRollNumber(), HttpMethod.GET, null, Student.class);
		Student val = response.getBody();
		if (val.getAttendance() > 90 && a.getTotalMarks() + 5 < 100) {
			a.setTotalMarks(a.getTotalMarks() + 5);
		}

		a.setPercentage(a.getTotalMarks());
		return resdao.getTotal(a);
	}

	public List<Results> findAll() {
		return resdao.findAll();
	}

	public Results getTopper() {
		List<Results> get = this.findAll();
		Results a = get.stream().sorted(Comparator.comparing(Results::getTotalMarks).reversed()).findFirst().get();
		return a;
	}

	public List<Results> get3Topper() {
		List<Results> get = this.findAll();
		List<Results> b = get.stream().sorted(Comparator.comparing(Results::getTotalMarks).reversed()).limit(3)
				.toList();
		return b;
	}

	public List<Results> between70and90() {
		List<Results> get = this.findAll();
		List<Results> c = get.stream().filter(i -> i.getTotalMarks() >= 70 && i.getTotalMarks() <= 90).toList();
		return c;
	}

}
