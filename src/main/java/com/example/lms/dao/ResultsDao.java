package com.example.lms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.Results;
import com.example.lms.repository.ResultRepository;

@Repository
public class ResultsDao {
	@Autowired
	ResultRepository repo;

	public Results getTotal(Results a) {
		return repo.save(a);

	}

	public List<Results> findAll() {
		return repo.findAll();
	}

}
