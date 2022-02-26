package com.mcan.rig.rest.controller;

import com.mcan.rig.mapper.OrderMapper;
import com.mcan.rig.persistance.entity.Order;
import com.mcan.rig.rest.request.OrderCreateRequest;
import com.mcan.rig.rest.response.OrderCreateResponse;
import com.mcan.rig.rest.response.OrderGetResponse;
import com.mcan.rig.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController (OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }


    @GetMapping("/{id}")
    public OrderGetResponse getById (@PathVariable Long id) {
        Order order = orderService.findById(id);
        return orderMapper.entityToGetResponse(order);

    }

    @GetMapping
    public List<OrderGetResponse> getByStartDateEndDate (@RequestParam("startDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date startDate,
                                                         @RequestParam("endDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date endDate) {
        List<Order> orders = orderService.findByDateInterval(startDate, endDate);
        return orders.stream().map(orderMapper::entityToGetResponse).collect(Collectors.toList());
    }

    @PostMapping
    public OrderCreateResponse create (@RequestBody OrderCreateRequest orderCreateRequest) {
        Order order = orderService.create(orderCreateRequest.getQuantity(), orderCreateRequest.getUsername(), orderCreateRequest.getBookId());
        return orderMapper.entityToCreateResponse(order);
    }
}
