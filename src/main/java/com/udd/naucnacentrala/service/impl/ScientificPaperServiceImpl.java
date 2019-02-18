package com.udd.naucnacentrala.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udd.naucnacentrala.domain.ScientificPaper;
import com.udd.naucnacentrala.repository.ScientificPaperRepository;
import com.udd.naucnacentrala.service.ScientificPaperService;

@Service
public class ScientificPaperServiceImpl implements ScientificPaperService {

	@Autowired
	private ScientificPaperRepository scientificPaperRepository;

	@Override
	public void save(ScientificPaper sp) {
		scientificPaperRepository.save(sp);
	}
		
}
