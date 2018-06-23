package com.luisskipthedishes.order.repository;

import com.luisskipthedishes.order.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {

}