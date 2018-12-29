package com.udd.naucnacentrala.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udd.naucnacentrala.domain.ScientificArea;
import com.udd.naucnacentrala.repository.ScientificAreaRepository;
import com.udd.naucnacentrala.service.ScientificAreaService;

@Service
public class ScientificAreaServiceImpl implements ScientificAreaService {

	@Autowired
	private ScientificAreaRepository scientificAreaRepository;
	
	public List<ScientificArea> getAll() {
		return scientificAreaRepository.findAll();
	}
	
}
