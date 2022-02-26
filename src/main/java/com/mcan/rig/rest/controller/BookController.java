package com.mcan.rig.rest.controller;

import com.mcan.rig.mapper.BookMapper;
import com.mcan.rig.persistance.entity.Book;
import com.mcan.rig.rest.request.BookCreateRequest;
import com.mcan.rig.rest.request.BookUpdateRequest;
import com.mcan.rig.rest.response.BookCreateResponse;
import com.mcan.rig.rest.response.BookGetResponse;
import com.mcan.rig.rest.response.BookUpdateResponse;
import com.mcan.rig.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController (BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }


    @GetMapping
    public List<BookGetResponse> findAll () {
        return bookService.findALl().stream().map(bookMapper::entityToGetResponse).collect(Collectors.toList());
    }

    @PostMapping
    public BookCreateResponse create (@RequestBody BookCreateRequest bookCreateRequest) {
        Book book = bookService.create(bookMapper.createRequestToEntity(bookCreateRequest));
        return bookMapper.entityToCreateResponse(book);
    }

    @PutMapping("/{id}")
    public BookUpdateResponse updateStock (@PathVariable Long id, @RequestBody BookUpdateRequest request) {
        Book book = bookService.update(id, request.getStock());
        return bookMapper.entityToUpdateResponse(book);
    }
}
