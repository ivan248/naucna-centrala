package com.udd.naucnacentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udd.naucnacentrala.domain.PaymentRecord;

@Repository
public interface PaymentRecordRepository extends JpaRepository<PaymentRecord, Long> {

}
