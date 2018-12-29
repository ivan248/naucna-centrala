package com.udd.naucnacentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udd.naucnacentrala.domain.ScientificArea;

@Repository
public interface ScientificAreaRepository extends JpaRepository<ScientificArea, Long> {

}
