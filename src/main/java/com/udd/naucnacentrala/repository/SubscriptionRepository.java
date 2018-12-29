package com.udd.naucnacentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udd.naucnacentrala.domain.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}
