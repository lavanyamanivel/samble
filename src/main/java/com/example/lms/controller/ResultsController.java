package com.example.lms.controller;

import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.lms.entity.MarkSheet;
import com.example.lms.entity.Results;
import com.example.lms.entity.Student;
import com.example.lms.service.ResultsService;

@RestController
@RequestMapping(value = "/resultDetails")
public class ResultsController {
	@Autowired
	ResultsService resser;
	@Autowired
	RestTemplate rest;

	@PostMapping(value = "/getTotal")
	public Results getTotal(@RequestBody Results a) {
		String url = "http://localhost:8080/studentDetails/getB/";
		ResponseEntity<Student> response = rest.exchange(url + a.getRollNumber(), HttpMethod.GET, null, Student.class);
		Student val = response.getBody();
		a.setName(val.getName());

		String url1 = "http://localhost:8081/markDetails/getDetails/";
		ResponseEntity<MarkSheet> response1 = rest.exchange(url1 + a.getRollNumber(), HttpMethod.GET, null,
				MarkSheet.class);
		MarkSheet val1 = response1.getBody();
		int b = val1.getSem1Total();
		int c = val1.getSem2Total();
		int total = (b + c) / 2;
		a.setTotalMarks(total);
		return resser.getTotal(a);
	}

	@GetMapping(value = "/findAll")
	public List<Results> findAll() {
		return resser.findAll();
	}

	@GetMapping(value = "/getTopeer")
	public Results getTopeer() {
		return resser.getTopper();
	}

	@GetMapping(value="/get3Topper")
	public List<Results>get3Topper() {
		return resser.get3Topper();
	}
	@GetMapping(value="/between70and90")
	public List<Results>between70and90(){
		return resser.between70and90();
	}
}
