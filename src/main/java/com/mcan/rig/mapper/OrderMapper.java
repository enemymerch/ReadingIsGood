package com.mcan.rig.mapper;

import com.mcan.rig.persistance.entity.Order;
import com.mcan.rig.rest.response.OrderCreateResponse;
import com.mcan.rig.rest.response.OrderGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    @Mapping(source = "customer.username", target = "customerUsername")
    @Mapping(source = "book.name", target = "bookName")
    public abstract OrderCreateResponse entityToCreateResponse (Order order);

    @Mapping(source = "customer.username", target = "customerUsername")
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.name", target = "bookName")
    @Mapping(source = "book.author", target = "bookAuthor")
    public abstract OrderGetResponse entityToGetResponse (Order order);
}
