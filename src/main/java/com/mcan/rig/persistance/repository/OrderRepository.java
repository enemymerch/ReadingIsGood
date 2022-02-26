package com.mcan.rig.persistance.repository;

import com.mcan.rig.persistance.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCreationDateIsBetween (Date startDate, Date endDate);

}
