package com.microservice.discstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.discstore.entity.Disc;

@Repository
public interface DiscRepository extends JpaRepository<Disc, Long> {

}
