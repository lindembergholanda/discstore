package com.discstore.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.discstore.payment.entity.Disc;

@Repository
public interface DiscRepository extends JpaRepository<Disc, Long> {

}
