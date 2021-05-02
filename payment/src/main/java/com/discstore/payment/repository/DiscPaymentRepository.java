package com.discstore.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.discstore.payment.entity.DiscPayment;

@Repository
public interface DiscPaymentRepository extends JpaRepository<DiscPayment, Long>{

}
