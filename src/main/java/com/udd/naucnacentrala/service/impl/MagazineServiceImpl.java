package com.udd.naucnacentrala.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udd.naucnacentrala.domain.Magazine;
import com.udd.naucnacentrala.repository.MagazineRepository;
import com.udd.naucnacentrala.service.MagazineService;

@Service
public class MagazineServiceImpl implements MagazineService {

	@Autowired
	private MagazineRepository magazineRepository;
	
	@Override
	public List<Magazine> getAll() {
		return magazineRepository.findAll();
	}

}
