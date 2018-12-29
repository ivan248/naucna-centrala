package com.udd.naucnacentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udd.naucnacentrala.domain.Magazine;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long> {

}
