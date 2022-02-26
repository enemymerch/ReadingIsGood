package com.mcan.rig.mapper;


import com.mcan.rig.persistance.entity.Book;
import com.mcan.rig.rest.request.BookCreateRequest;
import com.mcan.rig.rest.response.BookCreateResponse;
import com.mcan.rig.rest.response.BookGetResponse;
import com.mcan.rig.rest.response.BookUpdateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {


    BookGetResponse entityToGetResponse (Book book);

    Book createRequestToEntity (BookCreateRequest request);

    BookCreateResponse entityToCreateResponse (Book book);

    BookUpdateResponse entityToUpdateResponse (Book book);
}
