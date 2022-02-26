package com.mcan.rig.service;

import com.mcan.rig.persistance.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {

    List<Order> findAll ();

    Order create (Long quantity, String username, Long bookId);

    Order findById (Long id);

    List<Order> findByDateInterval (Date startDate, Date endDate);
}
