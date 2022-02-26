package com.mcan.rig.mapper;

import com.mcan.rig.persistance.entity.Customer;
import com.mcan.rig.rest.request.CustomerCreateRequest;
import com.mcan.rig.rest.response.CustomerCreateResponse;
import com.mcan.rig.rest.response.CustomerGetDetailedResponse;
import com.mcan.rig.rest.response.CustomerGetOrdersResponse;
import com.mcan.rig.rest.response.CustomerGetResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = OrderMapper.class)
public abstract class CustomerMapper {

    public abstract CustomerGetResponse entityToGetResponse (Customer entity);

    @AfterMapping
    void afterEntityToGetResponse (@MappingTarget CustomerGetResponse response, Customer entity) {
        String firstName = entity.getFirstName();
        String lastName = entity.getLastName();
        response.setName(firstName + " " + lastName);
    }

    @AfterMapping
    void afterEntityToCreateResponse (@MappingTarget CustomerCreateResponse response, Customer entity) {
        String firstName = entity.getFirstName();
        String lastName = entity.getLastName();
        response.setName(firstName + " " + lastName);
    }

    @AfterMapping
    void afterEntityToCustomerGetOrdersResponse (@MappingTarget CustomerGetOrdersResponse response, Customer entity) {
        String firstName = entity.getFirstName();
        String lastName = entity.getLastName();
        response.setName(firstName + " " + lastName);
    }

    public abstract CustomerGetDetailedResponse entityToGetDetailedResponse (Customer byUsername);

    public abstract Customer createRequestToEntity (CustomerCreateRequest customerCreateRequest);

    public abstract CustomerCreateResponse entityToCreateResponse (Customer customer);

    @Mapping(source = "orders", target = "orders")
    public abstract CustomerGetOrdersResponse entityToCustomerGetOrderResponse (Customer customer);
}

