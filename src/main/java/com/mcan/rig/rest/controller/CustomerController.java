package com.mcan.rig.rest.controller;

import com.mcan.rig.exception.CannotAccessOtherUsersDataException;
import com.mcan.rig.mapper.CustomerMapper;
import com.mcan.rig.rest.request.CustomerCreateRequest;
import com.mcan.rig.rest.response.CustomerCreateResponse;
import com.mcan.rig.rest.response.CustomerGetDetailedResponse;
import com.mcan.rig.rest.response.CustomerGetOrdersResponse;
import com.mcan.rig.rest.response.CustomerGetResponse;
import com.mcan.rig.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController (CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping("/all")
    public List<CustomerGetResponse> getAll () {
        return customerService.findALl().stream().map(customerMapper::entityToGetResponse).collect(Collectors.toList());
    }

    @GetMapping("/{username}")
    private CustomerGetDetailedResponse getByUserName (@PathVariable String username, Principal principal) {
        // going to validate username with principal , so user won't access each others data
        if (principal.getName().equals(username)) {
            return customerMapper.entityToGetDetailedResponse(customerService.findByUsername(username));
        }
        throw new CannotAccessOtherUsersDataException();
    }

    @PostMapping
    private CustomerCreateResponse create (@RequestBody CustomerCreateRequest customerCreateRequest) {
        return customerMapper.entityToCreateResponse(customerService.create(customerMapper.createRequestToEntity(customerCreateRequest)));
    }

    @GetMapping("/{username}/order")
    private CustomerGetOrdersResponse getAllOrdersOfCustomer (@PathVariable String username) {
        return customerMapper.entityToCustomerGetOrderResponse(customerService.findByUsername(username));
    }
}
