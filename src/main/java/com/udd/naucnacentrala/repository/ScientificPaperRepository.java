package com.udd.naucnacentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udd.naucnacentrala.domain.ScientificPaper;

@Repository
public interface ScientificPaperRepository extends JpaRepository<ScientificPaper, Long> {

}
